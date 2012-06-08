package ru.ics.engineerService.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * This abstract class describes all manipulations with cloud service
 * To use it you should extend this class and make functionality.
 * @author Lukashin {@link= "an.lukashin@gmail.com"}
 *
 */

public abstract class AbstractCloudManager {
	private Properties props;
	private static final Logger logger=Logger.getLogger(AbstractCloudManager.class);
	
	public String runVM(String properyName, String vmName){
		try {
			initProperties(properyName+".properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("No such properties. Name = "+properyName);
			return "Error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("I/O exception occured PropertyName = "+properyName);
			return "Error";
		}
		return runVirtualMachine(props,vmName);
	}
	
	/**
	 * Get connection properties from property file. Than request to run VM
	 * @param props
	 * @return result string with VM IP
	 */
	public abstract String runVirtualMachine(Properties props, String VMName);
	/**
	 * Than request to stop VM with this name
	 * @param props
	 * @return result
	 */
	public abstract String shutDownVirtualMachine(String vmName);
	private void initProperties(String propertyName) throws FileNotFoundException, IOException{
		props=new Properties();
		props.load(new FileInputStream(propertyName));
	}
}
