package com.ndovado.webapp.servlet.login;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ndovado.helpers.utente.UtenteHelper;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.bean.LoginBean;
import com.ndovado.webapp.bean.UtenteBean;
import com.ndovado.webapp.shared.ButtonMethod;
import com.ndovado.webapp.shared.HelperBase;

public class LoginControllerHelperServlet extends HelperBase {

	protected LoginBean data = new LoginBean();
	protected List<String> messaggiErrore = new ArrayList<String>();

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
	
	protected String jspAbsLocation(String page) {
		return "/" + page;
	}

	@ButtonMethod(buttonName = "editButton",isDefault = true)
	public String editMethod() {
		String address;
		// verifico se esiste un bean token di autenticazione
		UtenteBean ub = (UtenteBean) getObjectFromSession("utenteBean");
		if(ub!=null) {
			// token valido, redireziono alla pagina utente
			address = jspAbsLocation("AreaUtente.jsp");
		} else {
			// token non valido, redireziono alla pagina di inserimento dati
			address = jspLocation("Edit.jsp");
		} 
		return address;
	}

	@ButtonMethod(buttonName = "confirmButton")
	public String confirmMethod() {
		resetNullable();
		fillBeanFromRequest(data);
		String address;
		if (isValid(data)) {
			putObjectInSession("utenteBean", data);
			address = jspAbsLocation("AreaUtente.jsp");
		} else {
			address = jspLocation("Edit.jsp");
		}
		return address;
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

	
	public boolean isValid(LoginBean data) {
		boolean esitoControlloCoppia = UtenteHelper.verificaCredenzialiUtente(data.getMail(), data.getPassword());
		AppLogger.debug("Controllo credenziali [mail:"+data.getMail()+", password: "+data.getPassword()+"] esito verifica: "+esitoControlloCoppia);
		if (!esitoControlloCoppia) {
			appendMessageToRequest("Controlla le credenziali inserite.");
		}
		return super.isValid(data) && esitoControlloCoppia;
	}

	@Override
	public void resetNullable() {
	}
}
