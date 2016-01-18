package com.ndovado.helpers.core;

import java.util.ArrayList;
import java.util.List;

import com.ndovado.bridge.IBeanModelBridge;
import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.DescrizioneCamera;
import com.ndovado.dominio.core.Gestore;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.persistenza.base.CameraDAO;
import com.ndovado.tecservices.persistenza.base.DescrizioneCameraDAO;
import com.ndovado.tecservices.persistenza.base.LuogoDAO;
import com.ndovado.tecservices.persistenza.base.StrutturaDAO;
import com.ndovado.tecservices.persistenza.base.UtenteDAO;
import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.DescrizioneCameraBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;

public class StrutturaHelper implements IBeanModelBridge<StrutturaBean,Struttura>{

	private static StrutturaDAO sdao = new StrutturaDAO();
	private static DescrizioneCameraDAO dcdao = new DescrizioneCameraDAO();
	private static CameraDAO cdao = new CameraDAO();
	private static LuogoDAO ldao = new LuogoDAO();
	private static UtenteDAO udao = new UtenteDAO();
	
	private static StrutturaHelper instance;
	
	public static StrutturaHelper getInstance() {
		if (instance==null) {
			instance = new StrutturaHelper();
		}
		return instance;
	}
	
	
	public static List<StrutturaBean> getListaStruttureBeanByGestore(UtenteBean g) {
		List<StrutturaBean> struttureBean = new ArrayList<StrutturaBean>();
		
		if (g.getRuolo() == TipoUtente.GESTORE) {
			Utente gestore = udao.cercaUtentePerMail(g.getMail());
			if (gestore!=null) {
				Gestore ruolo = (Gestore) gestore.getRuolo();
				List<Struttura> struttureGestite = (List<Struttura>) ruolo.getStruttureGestite();
				for (Struttura struttura : struttureGestite) {
					struttureBean.add(new StrutturaBean(struttura));
				}
			}
		}
		return struttureBean;
	}
	
	public static StrutturaBean getStrutturaBeanByModel(Struttura s) {
		return getInstance().createBeanByModel(s);
	}
	
	@Override
	public StrutturaBean createBeanByModel(Struttura model) {
		return new StrutturaBean(model);
	}

	@Override
	public Struttura createModelByBean(StrutturaBean b) {
		return null;
	}

	@Override
	public StrutturaBean updateBeanByModel(Struttura model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Struttura updateModelByBean(StrutturaBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DescrizioneCamera creaOAggiornaDescrizioneCameraDaBean(DescrizioneCameraBean dcb) {
		DescrizioneCamera dcmodel;
		
		if (dcb.isNewBean()) {
			dcmodel = new DescrizioneCamera();
			fillDescrizioneCameraModelFromBean(dcb, dcmodel);
		}
		else {
			dcmodel = dcdao.get(dcb.getIdDescrizioneCamera());
			fillDescrizioneCameraModelFromBean(dcb, dcmodel);
		}
		return dcmodel;
	}


	private void fillDescrizioneCameraModelFromBean(DescrizioneCameraBean dcb, DescrizioneCamera dcmodel) {
		dcmodel.setDescrizioneCamera(dcb.getDescrizioneCamera());
		dcmodel.setPax(dcb.getPax());
		dcmodel.setPrezzoCamera(dcb.getPrezzoCamera());
		dcmodel.setDataInizioAffitto(dcb.getDataInizioAffitto());
		dcmodel.setDataFineAffitto(dcb.getDataFineAffitto());
	}
	
	public Camera creaOAggiornaCameraDaBean(CameraBean cb) {
		Camera cmodel;
		if (cb.isNewBean()) {
			cmodel = new Camera();
			fillCameraModelFromBean(cb, cmodel);
		} else {
			cmodel = cdao.get(cb.getIdCamera());
			fillCameraModelFromBean(cb, cmodel);
		}
		return cmodel;
	}
	
	private void fillCameraModelFromBean(CameraBean cb, Camera cmodel) {
		cmodel.setNomeCamera(cb.getNomeCamera());
		cmodel.setQuantity(cb.getQtyCamera());
		
		DescrizioneCameraBean dcb = cb.getDescCorrente();
		DescrizioneCamera descrizioneCorrente = creaOAggiornaDescrizioneCameraDaBean(dcb);
		cmodel.setDescrizioneCameraCorrente(descrizioneCorrente);
		
		List<DescrizioneCameraBean> descCamereBean = cb.getDescrizioni();
		for (DescrizioneCameraBean descrizioneCameraBean : descCamereBean) {
			DescrizioneCamera dcmodel = new DescrizioneCamera(cmodel);
			fillDescrizioneCameraModelFromBean(descrizioneCameraBean, dcmodel);
		}
	}
	
	public Struttura creaOAggiornaStrutturaFromBean(StrutturaBean sb) {
		Struttura smodel;
		if (sb.isNewBean()) {
			smodel = new Struttura();
			fillStrutturaModelFromBean(sb, smodel);
		} else {
			smodel = sdao.get(sb.getIdStruttura());
			fillStrutturaModelFromBean(sb, smodel);
		}
		return smodel;
	}
	
	private void fillStrutturaModelFromBean(StrutturaBean sb, Struttura smodel) {
		smodel.setNomeStruttura(sb.getNomeStruttura());
		Luogo l = ldao.get(sb.getLuogoStruttura().getIdLuogo());
		smodel.setLuogo(l);
		List<CameraBean> camereBean = sb.getCamere();
		for (CameraBean cameraBean : camereBean) {
			Camera cmodel = new Camera(smodel);
			fillCameraModelFromBean(cameraBean, cmodel); 
		}
	}
	
}
