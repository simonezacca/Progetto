package com.ndovado.webapp.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet(name="UtenteControllerServlet", urlPatterns="/utente/controller")
public class UtenteControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// ControllerEdit.jsp is only page that a GET request will see.
		UtenteControllerHelperServlet helper = new UtenteControllerHelperServlet(request, response);
		helper.doGet();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// ControllerEdit.jsp is only page that a GET request will see.
		UtenteControllerHelperServlet helper = new UtenteControllerHelperServlet(request, response);
		helper.doPost();
	}

}
