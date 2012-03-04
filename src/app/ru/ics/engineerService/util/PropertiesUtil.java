package ru.ics.engineerService.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Class for loading snapshot's properties from file
 * @author amoroz
 *
 */
public class PropertiesUtil {
	
	private static PropertiesUtil instance;
//	private static Properties props;

	/**
	 * Creates instance of singleton {@link PropertiesUtil} object instance or returns existing one.
	 * @return Singleton {@link PropertiesUtil} object instance.
	 */
	public static PropertiesUtil getInstance() {
	    if(instance == null) {
	    	 synchronized(PropertiesUtil.class) {
	    		 instance = new PropertiesUtil();
			}
		}
		return instance;
	}

	public Properties getProp(String fileName) {
//		if (props == null) {
		Properties	props = new Properties();
			try {
				props.load(PropertiesUtil.class.getResourceAsStream("/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
//		}
		return props;
	}
}