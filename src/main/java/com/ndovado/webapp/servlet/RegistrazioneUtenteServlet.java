package com.ndovado.webapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ndovado.controllers.utente.TokenAutenticazioneUtente;
import com.ndovado.controllers.utente.UtenteController;
import com.ndovado.dominio.core.Utente;

/**
 * Servlet implementation class RegistrazioneUtenteServlet
 */
public class RegistrazioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// oggetto sessione per il salvataggio del token di autenticazione
	private HttpSession session;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneUtenteServlet() {
        super();
    }

    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	
    	// TODO valutare l'uso di un oggetto servlet filter per validazione dati
    	String cognome = request.getParameter("cognome");
		String nome = request.getParameter("nome");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		Integer ruoloCode = Integer.valueOf(request.getParameter("ruolo"));

		try {
			if(UtenteController.verificaEsistenzaMail(mail)) {
				//redirezione pagina jps errore con messaggio
				request.setAttribute("messaggio", "Indirizzo mail esistente");
				
				response.sendRedirect("registrazioneUtente.jsp");
			} else {
				Utente u = UtenteController.creaNuovoUtente(cognome, nome, mail, password, ruoloCode);
				TokenAutenticazioneUtente token = UtenteController.creaTokenAutenticazione(u);
				
				session = request.getSession();
				session.setAttribute("tokenAutenticazione", token);
				
				response.sendRedirect("profiloUtente.jsp");
			}
		
		} catch (Exception e) {
			 
            e.printStackTrace();
        }
		
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

}
