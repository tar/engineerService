package ru.ics.engineerService.model;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.xensource.xenapi.APIVersion;
import com.xensource.xenapi.Connection;
import com.xensource.xenapi.Network;
import com.xensource.xenapi.Pool;
import com.xensource.xenapi.SR;
import com.xensource.xenapi.Session;
import com.xensource.xenapi.Task;
import com.xensource.xenapi.Types;
import com.xensource.xenapi.VIF;
import com.xensource.xenapi.VM;

import ru.ics.engineerService.util.Util;

/**
 * This class extends abstract manager ant contents methods to run XCP cloud vms
 * 
 * @author Lukashin 
 * 
 */

public class XCPCloudManager extends AbstractCloudManager {

	private static final Logger logger = Logger.getLogger(XCPCloudManager.class);
	private static final String PROPERTY_SERVER_NAME = "serverName";
	private static final String PROPERTY_USER_NAME = "userName";
	private static final String PROPERTY_PASSWORD = "password";

	private TargetServer targerServer = null;
	private static Connection connection;
	private static String connectionName;
	private static Map<String, VM> vms=new HashMap<String, VM>();

	@Override
	public String runVirtualMachine(Properties props, String vmName) {
		// TODO Auto-generated method stub
//		String serverName = "195.208.117.181";
//		String userName = "alukashin";
//		String password = "dee2ohLi";
		String serverName = props.getProperty(PROPERTY_SERVER_NAME);
		String userName = props.getProperty(PROPERTY_USER_NAME);
		String password = props.getProperty(PROPERTY_PASSWORD);
		if (Util.isEmpty(serverName) || Util.isEmpty(userName) || Util.isEmpty(password)) {
			logger.error("Empty properties recieved");
			logger.error("Server = " + serverName + "; User = " + userName + "; Password = " + password);
			return "Error!";
		}
		if (targerServer == null) {
			targerServer = new TargetServer(serverName, userName, password);
		}
		try {
			connect(targerServer);
			VM template = getFirstLinuxTemplate();
			logger.info("Template found: " + template.getNameLabel(connection));

			/* Clone the template */
			logger.info("Cloning the template...");
			Task cloning = template.createCloneAsync(connection, vmName);
			waitForTask(connection, cloning, 500);
			checkForSuccess(cloning);
			VM newVm = Types.toVM(cloning, connection);
			logger.info("New VM clone: " + newVm.getNameLabel(connection));

			/* Find a storage repository */
			SR defaultSR = getDefaultSR();
			logger.info("Default SR: " + defaultSR.getNameLabel(connection));

			/* Find a network */
			Network network = getFirstNetwork();
			logger.info("Network chosen: " + network.getNameLabel(connection));

			/*
			 * We have our clone and our network, attach them to each other with a VIF
			 */
			makeVIF(newVm, network, "0");

			/* Put the SR uuid into the provision XML */
			Map<String, String> otherConfig = newVm.getOtherConfig(connection);
			String disks = otherConfig.get("disks");
			disks = disks.replace("sr=\"\"", "sr=\"" + defaultSR.getUuid(connection) + "\"");
			otherConfig.put("disks", disks);
			newVm.setOtherConfig(connection, otherConfig);
			vms.put(vmName, newVm);
			/* Now provision the disks */
			logger.info("provisioning... ");
			Task provisioning = newVm.provisionAsync(connection);
			waitForTask(connection, provisioning, 5000);
			checkForSuccess(provisioning);
			logger.info("provisioned");

			/* Should have done the trick. Let's see if it starts. */
			logger.info("Starting new VM.....");
			Task t = newVm.startAsync(connection, false, false);
			waitForTask(connection, t, 250);
			checkForSuccess(t);
			logger.info("started");

			/* and shut it down */
//			logger.info("Shutting it down.....");
//			t = newVm.cleanShutdownAsync(connection);
//			waitForTask(connection, t, 500);
//			logger.info("Shut down.");
			shutDownVirtualMachine(vmName);
			return "Success";
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			return "Error!";
		}finally{
			try {
				disconnect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
		}
	}

	@Override
	public String shutDownVirtualMachine(String vmName) {
		// TODO Auto-generated method stub
		if (targerServer == null) {
			logger.info("No connection to server. No VM running");
			return "Success";
		}
		logger.info("Shutting it down.....");
		VM vm=vms.get(vmName);
		try {
			Task t = vm.cleanShutdownAsync(connection);
			waitForTask(connection, t, 500);
			vms.remove(vmName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			return "Error!";
		}
		logger.info("Shut down.");
		return "Success";
	}

	protected static void connect(TargetServer target) throws Exception {
		/*
		 * Old style: Connection constructor performs login_with_password for you. Deprecated.
		 * 
		 * connection = new Connection(target.Hostname, target.Username, target.Password);
		 */

		/*
		 * New style: we are responsible for Session login/logout.
		 */
		connection = new Connection(new URL("http://" + target.Hostname));
		logger.info(String.format("logging in to '%s' as '%s' with password '%s'...", target.Hostname, target.Username,
				target.Password));
		logger.info("Success");
		Session.loginWithPassword(connection, target.Username, target.Password, APIVersion.latest().toString());
		logger.info(String.format("Session API version is %s", connection.getAPIVersion().toString()));

		connectionName = target.Hostname;
	}

	protected static void disconnect() throws Exception {
		logger.info("disposing connection for " + connectionName);
		Session.logout(connection);
	}

	protected static void hRule() {
		logger.info("----------------------------------------------------------------------");
	}

	/**
	 * Given a task in progress, sleeps until it completes, waking to print status reports periodically.
	 */
	protected static void waitForTask(Connection c, Task task, int delay) throws Exception {
		while (task.getStatus(c) == Types.TaskStatusType.PENDING) {
			logger.info(task.getProgress(c));
			Thread.sleep(delay);
		}
		logger.info("");
	}

	protected static SR getDefaultSR() throws Exception {
		Set<Pool> pools = Pool.getAll(connection);
		Pool pool = (pools.toArray(new Pool[0]))[0];
		return pool.getDefaultSR(connection);
	}

	protected static VM getFirstWindowsTemplate() throws Exception {
		Map<VM, VM.Record> all_recs = VM.getAllRecords(connection);
		for (Map.Entry<VM, VM.Record> e : all_recs.entrySet()) {
			if (e.getValue().isATemplate == true && e.getValue().nameLabel.contains("Windows")) {
				return e.getKey();
			}
		}

		throw new Exception("No Windows templates found!");
	}

	protected static VM getFirstLinuxTemplate() throws Exception {
		Map<VM, VM.Record> all_recs = VM.getAllRecords(connection);
		for (Map.Entry<VM, VM.Record> e : all_recs.entrySet()) {
			if (e.getValue().isATemplate == true && e.getValue().nameLabel.contains("Linux")) {
				return e.getKey();
			}
		}

		throw new Exception("No Linux templates found!");
	}

	/**
	 * Finds the first network (probably the one created by AddNetwork.java).
	 */
	protected static Network getFirstNetwork() throws Exception {
		Set<Network> networks = Network.getAll(connection);
		for (Network i : networks) {
			return i;
		}

		throw new Exception("No networks found!");
	}

	/* Assert that a task has succeeded. Throw an exception if not */
	private static void checkForSuccess(Task task) throws Exception {
		if (task.getStatus(connection) == Types.TaskStatusType.SUCCESS) {
			logger.info("task succeeded");
		} else {
			throw new Exception("Task failed! Task record:\n" + task.getRecord(connection));
		}
	}

	/*
	 * Create a VIF by making a VIF.record and then filling in the necessary fields
	 */
	private static VIF makeVIF(VM newVm, Network defaultNetwork, String device) throws Exception {
		VIF.Record newVifRecord = new VIF.Record();

		// These three parameters are used in the command line VIF creation
		newVifRecord.VM = newVm;
		newVifRecord.network = defaultNetwork;
		newVifRecord.device = device;

		// These appear to be necessary
		newVifRecord.MTU = 1500L;
		newVifRecord.qosAlgorithmType = "";
		newVifRecord.qosAlgorithmParams = new HashMap<String, String>();
		newVifRecord.otherConfig = new HashMap<String, String>();

		/* Create the VIF by asynchronous means */
		logger.info("Creating a VIF");
		Task task1 = VIF.createAsync(connection, newVifRecord);
		waitForTask(connection, task1, 0);

		/*
		 * Now deliberately screw things up by creating a second VIF with the same parameters.
		 */
		Task task2;
		logger.info("Deliberately causing an error by trying to create the same VIF twice: ");
		task2 = VIF.createAsync(connection, newVifRecord);
		waitForTask(connection, task2, 0);
		/* This should all go through, but the task shouldn't have succeeded */
		try {
			checkForSuccess(task2);
		} catch (Exception e) {
			logger.info("Exception duly thrown");
		}

		/*
		 * However, the first call should have worked, so we can get its result and use that
		 */
		checkForSuccess(task1);
		return Types.toVIF(task1, connection);
	}
	
	public static void main(String[] args) {
		XCPCloudManager xcp=new XCPCloudManager(); 
        	xcp.runVirtualMachine(new Properties(), "test");
	}
}
