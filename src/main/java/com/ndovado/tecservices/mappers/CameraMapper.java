package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.core.Camera;
import com.ndovado.webapp.beans.core.CameraBean;

public class CameraMapper extends AGenericMapper<CameraBean, Camera> {
	
	private static volatile CameraMapper instance;
	
	private CameraMapper() {}
	
	public static CameraMapper getInstance() {
		if (instance==null) {
			synchronized (CameraMapper.class) {
				if (instance==null)
					instance = new CameraMapper();
			}
		}
		return instance;
	}
	
	
	@Override
	public CameraBean getBeanFromModel(Camera model) {
		CameraBean cbean = mapper.map(model, CameraBean.class);
		return cbean;
	}

	@Override
	public Camera getModelFromBean(CameraBean bean) {
		Camera cmodel = mapper.map(bean, Camera.class);
		return cmodel;
	}

}
