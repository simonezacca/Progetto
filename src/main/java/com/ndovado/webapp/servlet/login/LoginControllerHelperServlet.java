package com.ndovado.webapp.servlet.login;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.ndovado.controllers.utente.UtenteControllerDominio;
import com.ndovado.webapp.bean.LoginBean;
import com.ndovado.webapp.bean.UtenteBean;
import com.ndovado.webapp.shared.ButtonMethod;
import com.ndovado.webapp.shared.HelperBase;

public class LoginControllerHelperServlet extends HelperBase {

	protected LoginBean data = new LoginBean();
	protected List<String> messaggiErrore = new ArrayList<>();

	public LoginControllerHelperServlet(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public Object getData() {
		return data;
	}

	protected void copyFromSession(Object sessionHelper) {
		if (sessionHelper.getClass() == this.getClass()) {
			LoginControllerHelperServlet helper = (LoginControllerHelperServlet) sessionHelper;
			data = helper.data;
			checked = helper.checked;
			selected = helper.selected;
		}
	}

	protected String jspLocation(String page) {
		return "/WEB-INF/login/" + page;
	}

	@ButtonMethod(buttonName = "editButton", isDefault = true)
	public String editMethod() {
		return jspLocation("Edit.jsp");
	}

	@ButtonMethod(buttonName = "confirmButton")
	public String confirmMethod() {
		resetNullable();
		fillBeanFromRequest(data);
		setCheckedAndSelected(data);
		// The next JSP address depends on the validity of the data.
		String address;
		if (isValid(data)) {
			address = jspLocation("Confirm.jsp");
		} else {
			address = jspLocation("Edit.jsp");
		}
		return address;
	}

	@ButtonMethod(buttonName = "processButton")
	public String processMethod() {
		if (!isValid(data)) {
			return jspLocation("Edit.jsp");
		}
		//data = UtenteControllerDominio.creaOAggiornaUtenteDaBean(data);
	
		return jspLocation("Process.jsp");
	}

	@Override
	protected void doGet() throws ServletException, java.io.IOException {
//		if (HibernateHelper.testDB(response)) {
		if (true) {
			// Call the method with false to place a new helper in the session
			addHelperToSession("helper", SessionData.IGNORE);
			// Edit.jsp is the only page that will be displayed from a GET
			// request.
			String address = editMethod();
			request.getRequestDispatcher(address).forward(request, response);
		}
	}

	@Override
	protected void doPost() throws ServletException, java.io.IOException {
//		if (HibernateHelper.testDB(response)) {
		if (true) {
			addHelperToSession("helper", SessionData.READ);
			String address = executeButtonMethod();
			request.getRequestDispatcher(address).forward(request, response);
		}
	}

	
	public boolean isValid(UtenteBean data) {
		boolean esitoMail = UtenteControllerDominio.esisteIndirizzoMail(data.getMail());
		if(esitoMail && data.isNewBean()) {
			String messaggio = "Indirizzo mail esistente";
			messaggiErrore.add(messaggio);
			request.setAttribute("messaggiErrore", messaggiErrore);
		}
		return super.isValid(data) && !esitoMail;
	}

	@Override
	public void resetNullable() {
	}
}
