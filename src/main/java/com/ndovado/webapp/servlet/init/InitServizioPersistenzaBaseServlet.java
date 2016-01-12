package com.ndovado.webapp.servlet.init;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import com.ndovado.tecservices.persistenza.base.ServizioPersistenzaBase;

/**
 * Servlet implementation class InitServizioPersistenzaBase
 */
public class InitServizioPersistenzaBaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() {
		boolean start = Boolean.parseBoolean(getServletConfig().getInitParameter("start"));
		if(start)
			ServizioPersistenzaBase.getSessionFactory();
	}

}
