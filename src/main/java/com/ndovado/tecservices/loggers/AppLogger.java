package com.ndovado.tecservices.loggers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 */
public class AppLogger {

	/**
	 * riferimento ad un'istanza dell'oggetto logger di Log4J
	 */
	private static Logger logger = LogManager.getLogger(AppLogger.class);
	
	
	public static void init(String fileName) {
		PropertyConfigurator.configure(fileName);
	}

	/**
	 * @param args[]
	 */
	public static void main(String args[]) {
		PropertyConfigurator.configure(System.getProperty("basepath") + "/" + "log4j.properties");

	    debug("Hello");
	}

	/**
	 * @param message
	 */
	public static void info(String message) {
		logger.info(message);
	}


	/**
	 * @param message
	 */
	public static void debug(String message) {
		logger.debug(message);
	}


	/**
	 * @param message
	 */
	public static void error(String message) {
		logger.error(message);
	}


	/**
	 * @param message
	 */
	public static void warn(String message) {
		logger.warn(message);
	}

}