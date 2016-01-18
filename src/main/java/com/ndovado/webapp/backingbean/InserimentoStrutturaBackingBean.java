package com.ndovado.webapp.backingbean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

import com.ndovado.dominio.core.Utente;
 
@ManagedBean
@ViewScoped
public class InserimentoStrutturaBackingBean implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Utente user = new Utente();
     
    private boolean skip;
     
    public Utente getUser() {
        return user;
    }
 
    public void setUser(Utente user) {
        this.user = user;
    }
     
    public void save() {        
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getMail());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
}
