package com.ndovado.bridge;

public interface IBeanModelBridge<B,M> {
	
	abstract B createBeanByModel(M model);
	
	public M createModelByBean(B bean);
	
	public B updateBeanByModel(M model);
	
	public M updateModelByBean(B bean);
}
