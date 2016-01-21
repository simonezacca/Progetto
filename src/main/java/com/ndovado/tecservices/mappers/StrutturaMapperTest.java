package com.ndovado.tecservices.mappers;

import java.util.Calendar;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistenza.base.LuogoDAO;
import com.ndovado.tecservices.persistenza.base.StrutturaDAO;
import com.ndovado.tecservices.persistenza.base.UtenteDAO;
import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.DescrizioneCameraBean;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;

public class StrutturaMapperTest {

	public static void main(String[] args) {
		
		UtenteDAO udao = new UtenteDAO();
		Utente umodel = udao.cercaUtentePerMail("antonio.schiazza@gmail.com");
		UtenteBean ubean = UserMapper.getInstance().getBeanFromModel(umodel);
		AppLogger.debug("Ruolo UtenteBean:"+ubean.getRuolo().toString());
		
		
		AppLogger.debug("Creo model Luogo");
		Luogo l = new LuogoDAO().get(new Long(1));
		AppLogger.debug("Converto model Luogo in LuogoBean");
		LuogoBean lb = LuogoMapper.getInstance().getBeanFromModel(l);
		AppLogger.debug("bean: "+lb);
		
		
		AppLogger.debug("Creo StrutturaBean");
		StrutturaBean sb = new StrutturaBean(ubean);
		AppLogger.debug("Imposto luogo LuogoBean in StrutturaBean");
		sb.setLuogoStruttura(lb);
		
		
		AppLogger.debug("Imposto nome struttura in StrutturaBean");
		sb.setNomeStruttura("Struttura Test");
		
		AppLogger.debug("Creo CameraBean e associo StrutturaBean con CameraBean");
		CameraBean c = new CameraBean(sb);
		
		AppLogger.debug("Imposto nome CameraBean");
		c.setNomeCamera("Camera Prova");
		AppLogger.debug("Imposto Qty CameraBean");
		c.setQtyCamera(2);
		
		AppLogger.debug("Aggiungo CameraBean in elenco camere di StrutturaBean");
		sb.addCameraBean(c);
		
		AppLogger.debug("Creo DescrizioneCameraBean");
		DescrizioneCameraBean dcb = new DescrizioneCameraBean(c);
		dcb.setDescrizioneCamera("Descrizione camera");
		Calendar cal = Calendar.getInstance();
		
		cal.set(2016,0,19);
		dcb.setDataInizioAffitto(cal.getTime());
		
		cal.set(2016,0,22);
		dcb.setDataFineAffitto(cal.getTime());
		dcb.setPax(2);
		dcb.setPrezzoCamera(new Float(30));
		
		AppLogger.debug("Associo DescrizioneCameraBean a CameraBean");
		c.setDescrizioneCorrente(dcb);
		
		AppLogger.debug("Converto bean StrutturaBean in model Struttura");
		Struttura smodel = StrutturaMapper.getInstance().getModelFromBean(sb);
		
		AppLogger.debug("Struttura model: "+smodel.toString());
		
		AppLogger.debug("Luogo Model Struttura: "+smodel.getLuogoStruttura().toString());
		
		AppLogger.debug("Camera Model Struttura: "+smodel.getCamereInserite().get(0).toString());
		
		StrutturaDAO sdao = new StrutturaDAO();
		AppLogger.debug("Persisto model Struttura");
		sdao.saveOrUpdate(smodel);
		
		AppLogger.debug("Creo nuovo bean StrutturaBean da model Struttura");
		sb = StrutturaMapper.getInstance().getBeanFromModel(smodel);
		AppLogger.debug(sb.toString());
		
		
		AppLogger.debug("Verifica IDENTITA'");
		
		StrutturaBean sb1 = StrutturaMapper.getInstance().getBeanFromModel(smodel);
		
		if (sb==sb1)
			AppLogger.debug("sb == sb1 in JVM");
		else AppLogger.debug("sb != sb1 in JVM");
		
		if(sb.equals(sb1))
			AppLogger.debug("sb EQUALS sb1 by Values");
		else
			AppLogger.debug("sb NOT EQUALS sb1 by Values");	
		
		smodel = StrutturaMapper.getInstance().getModelFromBean(sb);
		Struttura smodel1 = StrutturaMapper.getInstance().getModelFromBean(sb1);
		
		if (smodel==smodel1)
			AppLogger.debug("smodel == smodel1 in JVM");
		else AppLogger.debug("smodel != smodel1 in JVM");
		
		if(smodel.equals(smodel1))
			AppLogger.debug("smodel EQUALS smodel1 by Values");
		else
			AppLogger.debug("smodel NOT EQUALS smodel1 by Values");	
		
	}

}
