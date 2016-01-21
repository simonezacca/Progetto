package com.ndovado.helpers.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.DescrizioneCamera;
import com.ndovado.dominio.core.Gestore;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistenza.base.CameraDAO;
import com.ndovado.tecservices.persistenza.base.DescrizioneCameraDAO;
import com.ndovado.tecservices.persistenza.base.LuogoDAO;
import com.ndovado.tecservices.persistenza.base.StrutturaDAO;
import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.DescrizioneCameraBean;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;

@ManagedBean(name="strutturaHelper")
public class StrutturaHelper{

	private static StrutturaDAO sdao = new StrutturaDAO();
	private static DescrizioneCameraDAO dcdao = new DescrizioneCameraDAO();
	private static CameraDAO cdao = new CameraDAO();
	private static LuogoDAO ldao = new LuogoDAO();
	
	private static StrutturaHelper instance;
	
	public static StrutturaHelper getInstance() {
		if (instance==null) {
			instance = new StrutturaHelper();
		}
		return instance;
	}
	
	
	public List<StrutturaBean> getListaStruttureBeanByGestore(UtenteBean g) {
		List<StrutturaBean> struttureBean = new ArrayList<StrutturaBean>();
		
		if (g.getRuolo() instanceof GestoreBean) {
			Utente gestore = UtenteHelper.getUtenteModelFromBean(g);
			if (gestore!=null) {
				Gestore ruolo = (Gestore) gestore.getRuolo();
				
				Set<Struttura> struttureGestite = ruolo.getStruttureGestite();
				for (Struttura struttura : struttureGestite) {
					StrutturaBean sb = new StrutturaBean(struttura);
					sb.setGestore(g);
					LuogoBean lb = new LuogoBean(struttura.getLuogo());
					sb.setLuogoStruttura(lb);
					struttureBean.add(sb);
				}
			}
		}
		return struttureBean;
	}
	
	public static StrutturaBean getStrutturaBeanByModel(Struttura s) {
		return new StrutturaBean(s);
	}
	
	public DescrizioneCamera creaOAggiornaDescrizioneCameraDaBean(DescrizioneCameraBean dcb,Camera cmodel) {
		DescrizioneCamera dcmodel;
		
		if (dcb.isNewBean()) {
			dcmodel = new DescrizioneCamera(cmodel);
		}
		else {
			dcmodel = dcdao.get(dcb.getId());
		}
		fillDescrizioneCameraModelFromBean(dcb, dcmodel);
		// persisto la descrozione camera
		dcdao.saveOrUpdate(dcmodel);
		return dcmodel;
	}


	private void fillDescrizioneCameraModelFromBean(DescrizioneCameraBean dcb, DescrizioneCamera dcmodel) {
		dcmodel.setDescrizioneCamera(dcb.getDescrizioneCamera());
		dcmodel.setPax(dcb.getPax());
		dcmodel.setPrezzoCamera(dcb.getPrezzoCamera());
		dcmodel.setDataInizioAffitto(dcb.getDataInizioAffitto());
		dcmodel.setDataFineAffitto(dcb.getDataFineAffitto());
	}
	
	public Camera creaOAggiornaCameraDaBean(CameraBean cb,Struttura smodel) {
		Camera cmodel;
		if (cb.isNewBean()) {
			cmodel = new Camera(smodel);
		} else {
			cmodel = cdao.get(cb.getId());
		}
		fillCameraModelFromBean(cb, cmodel);
		// persisto la struttura e tutte le entità collegate in cascata
		cdao.saveOrUpdate(cmodel);
		return cmodel;
	}
	
	private void fillCameraModelFromBean(CameraBean cb, Camera cmodel) {
		AppLogger.debug("Imposto nome Camera Model <--- Camera Bean");
		cmodel.setNomeCamera(cb.getNomeCamera());
		AppLogger.debug("Imposto qty Camera Model <--- Camera Bean");
		cmodel.setQtyCamera(cb.getQtyCamera());
		
		DescrizioneCameraBean dcb = cb.getDescrizioneCorrente();
		AppLogger.debug("Creo nuova Descrizione Camera Model");
		DescrizioneCamera descrizioneCorrenteModel = new DescrizioneCamera(cmodel);
		AppLogger.debug("Imposto Descrizione Camera Model <--- Descrizione Camera Bean");
		cmodel.setDescrizioneCorrente(descrizioneCorrenteModel);
		fillDescrizioneCameraModelFromBean(dcb, descrizioneCorrenteModel);
				
		descrizioneCorrenteModel.setCameraAssociata(cmodel);
		cmodel.setDescrizioneCorrente(descrizioneCorrenteModel);
		
		List<DescrizioneCameraBean> descCamereBean = cb.getDescrizioniCamera();
		for (DescrizioneCameraBean descrizioneCameraBean : descCamereBean) {
			DescrizioneCamera dcmodel = new DescrizioneCamera(cmodel);
			fillDescrizioneCameraModelFromBean(descrizioneCameraBean, dcmodel);
		}
	}
	
	public Struttura creaOAggiornaStrutturaFromBean(StrutturaBean sb) {
		// ottengo un modello di gestore da associare alla struttura
		Utente gestore = UtenteHelper.getUtenteModelFromBean(sb.getGestore());
		Struttura smodel;
		if (sb.isNewBean()) {
			AppLogger.debug("Nuova struttura da Bean");
			smodel = new Struttura();
			// lego la struttura con il proprietario
			smodel.setGestore((Gestore) gestore.getRuolo());
			
		} else {
			AppLogger.debug("Struttura esistente da Bean");
			smodel = sdao.get(sb.getId());
		}
		AppLogger.debug("Riempio struttura da Bean");
		fillStrutturaModelFromBean(sb, smodel);
		// persisto la struttura e tutte le entità connesse in cascata
		sdao.saveOrUpdate(smodel);
		return smodel;
	}
	
	private void fillStrutturaModelFromBean(StrutturaBean sb, Struttura smodel) {
		smodel.setNomeStruttura(sb.getNomeStruttura());
		Luogo l = ldao.get(sb.getLuogoStruttura().getId());
		smodel.setLuogoStruttura(l);
		
		AppLogger.debug("Ottengo elenco camere da Struttura Bean");
		List<CameraBean> camereBean = sb.getCamereInserite();
		for (CameraBean cameraBean : camereBean) {
			AppLogger.debug("Creo nuovo Camera Model");
			Camera cmodel = new Camera(smodel);
			AppLogger.debug("Riempio Camera Model <--- Camera Bean");
			fillCameraModelFromBean(cameraBean, cmodel); 
		}
	}
	
}
