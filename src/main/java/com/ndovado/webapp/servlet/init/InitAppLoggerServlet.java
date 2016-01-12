package com.ndovado.webapp.servlet.init;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import com.ndovado.tecservices.loggers.AppLogger;

public class InitAppLoggerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) {
		//boolean start = Boolean.parseBoolean(getServletConfig().getInitParameter("start"));
//		if (start) {
		if (true) {	
			ServletContext sc = config.getServletContext();
			String webAppPath = sc.getRealPath("/");
			String log4jProp = webAppPath + "WEB-INF/classes/log4j.properties";
			AppLogger.init(log4jProp);
		}
	}
}
