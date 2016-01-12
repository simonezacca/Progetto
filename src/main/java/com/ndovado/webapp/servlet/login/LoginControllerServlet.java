package com.ndovado.webapp.servlet.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// ControllerEdit.jsp is only page that a GET request will see.
		LoginControllerHelperServlet helper = new LoginControllerHelperServlet(request, response);
		helper.doGet();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// ControllerEdit.jsp is only page that a GET request will see.
		LoginControllerHelperServlet helper = new LoginControllerHelperServlet(request, response);
		helper.doPost();
	}

}
