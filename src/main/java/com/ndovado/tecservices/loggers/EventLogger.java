package com.ndovado.tecservices.loggers;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 */
public class EventLogger {

	/**
	 * Default constructor
	 */
	public EventLogger() {
	}

	/**
	 * riferimento ad un'istanza dell'oggetto logger di Log4J
	 */
	private Logger logger;

	/**
	 * @param fileName
	 */
	public void init(String fileName) {
		PropertyConfigurator.configure(fileName);
	}

	/**
	 * @param args[]
	 */
	public void main(String args[]) {
		PropertyConfigurator.configure(System.getProperty("basepath") + "/" + "log4j.properties");

	    debug("Hello");
	}

	/**
	 * @param message
	 */
	public void info(String message) {
		logger.info(message);
	}

	/**
	 * @param message
	 */
	public void debug(String message) {
		logger.debug(message);
	}

	/**
	 * @param message
	 */
	public void error(String message) {
		logger.error(message);
	}

	/**
	 * @param message
	 */
	public void warn(String message) {
		logger.warn(message);
	}

}