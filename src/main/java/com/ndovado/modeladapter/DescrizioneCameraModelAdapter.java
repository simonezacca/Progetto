package com.ndovado.modeladapter;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.DescrizioneCamera;
import com.ndovado.tecservices.persistenza.base.DescrizioneCameraDAO;
import com.ndovado.webapp.beans.core.DescrizioneCameraBean;

public class DescrizioneCameraModelAdapter extends GenericModelAdapter<DescrizioneCameraBean, DescrizioneCamera> {

	private static DescrizioneCameraDAO dcdao = new DescrizioneCameraDAO();
	private static DescrizioneCameraModelAdapter instance;
	
	private DescrizioneCameraModelAdapter() {}
	
	public static DescrizioneCameraModelAdapter getInstance() {
		if (instance==null) {
			instance = new DescrizioneCameraModelAdapter();
		}
		return instance;
	}
	
	@Override
	public DescrizioneCamera getModelFromBean(DescrizioneCameraBean bean) {
		if (!bean.isNewBean()) {
			return dcdao.get(bean.getId());
		}
		return null;
	}

	@Override
	protected void fillModelFromBean(DescrizioneCameraBean bean, DescrizioneCamera model) {
		model.setDescrizioneCamera(bean.getDescrizioneCamera());
		model.setPax(bean.getPax());
		model.setPrezzoCamera(bean.getPrezzoCamera());
		model.setDataInizioAffitto(bean.getDataInizioAffitto());
		model.setDataFineAffitto(bean.getDataFineAffitto());
	}
	
	public DescrizioneCamera createOrUpdateModelFromBean(DescrizioneCameraBean bean, Camera cameraModel) {
		DescrizioneCamera model;
		if (bean.isNewBean()) {
			model = new DescrizioneCamera(cameraModel);
		} else {
			model = dcdao.get(bean.getId());
		}
		fillModelFromBean(bean, model);
		return model;
	}

}
