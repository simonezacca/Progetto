package com.ndovado.modeladapter;

public abstract class GenericModelAdapter<B,M> {

	public abstract M getModelFromBean(B bean);
	
	
	/**
	 * Riempire solo le proprietà. NON impostare id dal bean
	 * @param bean
	 * @param model
	 */
	protected abstract void fillModelFromBean(B bean, M model);
	
}
