package com.ndovado.webapp.servlet.utente;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ndovado.helpers.utente.UtenteHelper;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.bean.UtenteBean;
import com.ndovado.webapp.shared.ButtonMethod;
import com.ndovado.webapp.shared.HelperBase;


public class UtenteControllerHelperServlet extends HelperBase {

	protected UtenteBean data = new UtenteBean();
	protected List<String> messaggiErrore = new ArrayList<>();

	public UtenteControllerHelperServlet(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public Object getData() {
		return data;
	}

	protected void copyFromSession(Object sessionHelper) {
		if (sessionHelper.getClass() == this.getClass()) {
			UtenteControllerHelperServlet helper = (UtenteControllerHelperServlet) sessionHelper;
			data = helper.data;
			checked = helper.checked;
			selected = helper.selected;
		}
	}

	protected String jspLocation(String page) {
		return "/WEB-INF/utente/" + page;
	}

	@ButtonMethod(buttonName = "editButton", isDefault = true)
	public String editMethod() {
		return jspLocation("Edit.jsp");
	}

	@ButtonMethod(buttonName = "confirmButton")
	public String confirmMethod() {
		messaggiErrore.clear();
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
		data = UtenteHelper.creaOAggiornaUtenteDaBean(data);
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

	public void resetNullable() {
		data.setRuolo("locatario");
	}
	
	public boolean isValid(UtenteBean data) {
		boolean esitoMail = UtenteHelper.esisteIndirizzoMail(data.getMail());
		AppLogger.debug("esitoMail: "+esitoMail);
		AppLogger.debug("data.isNewBean(): "+data.isNewBean());
		if(esitoMail && data.isNewBean()) {
			// caso inserimento nuovo utente
			String messaggio = "Indirizzo mail esistente";
			messaggiErrore.add(messaggio);
			request.setAttribute("messaggiErrore", messaggiErrore);
		} else if(esitoMail && !data.isNewBean()) {
			// caso aggiornamento utente esistente
			esitoMail = false;
		}
		return super.isValid(data) && !esitoMail;
	}
}
