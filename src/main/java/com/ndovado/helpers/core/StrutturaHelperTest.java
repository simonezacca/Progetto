package com.ndovado.helpers.core;

import java.util.Calendar;
import java.util.List;

import com.ndovado.dominio.core.Luogo;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistenza.base.LuogoDAO;
import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.DescrizioneCameraBean;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;

public class StrutturaHelperTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UtenteBean ub = UtenteHelper.creaUtenteBeanDaMail("schiazza@mail.com");
		
		Luogo l = new LuogoDAO().get(new Long(21));
		LuogoBean lb = LuogoHelper.creaLuogoBeanDaModel(l);
		
		StrutturaBean sb = new StrutturaBean(ub);
		sb.setGestore(ub);
		sb.setLuogoStruttura(lb);
		sb.setNomeStruttura("Struttura Test");
		
		CameraBean c = new CameraBean(sb);
		c.setNomeCamera("Camera Prova");
		c.setQtyCamera(2);
		
		sb.addCameraBean(c);
		
		DescrizioneCameraBean dcb = new DescrizioneCameraBean();
		dcb.setDescrizioneCamera("Descrizione camera");
		Calendar cal = Calendar.getInstance();
		
		cal.set(2016,0,19);
		dcb.setDataInizioAffitto(cal.getTime());
		
		cal.set(2016,0,22);
		dcb.setDataFineAffitto(cal.getTime());
		dcb.setPax(2);
		dcb.setPrezzoCamera(new Float(30));
		
		c.setDescrizioneCorrente(dcb);
		
		StrutturaHelper.getInstance().creaOAggiornaStrutturaFromBean(sb);
	
		AppLogger.debug("Ottengo lista struttura per UtenteBean:"+ub.getMail());
		List<StrutturaBean> struttureGestite = StrutturaHelper.getInstance().getListaStruttureBeanByGestore(ub);
		for (StrutturaBean strutturaBean : struttureGestite) {
			AppLogger.debug(strutturaBean.toString());
		}
		
	}

}
