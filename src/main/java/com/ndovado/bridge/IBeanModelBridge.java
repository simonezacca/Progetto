package com.ndovado.bridge;

public interface IBeanModelBridge {
	
	abstract IBean createBeanByModel(IModel model);
	
	public IModel createModelByBean(IBean bean);
	
	public IBean updateBeanByModel(IModel model);
	
	public IModel updateModelByBean(IBean bean);
}
