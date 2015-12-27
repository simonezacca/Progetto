package com.ndovado.dominio.pagamenti;

/**
 * 
 */
public class GWAutorizzazionePagamenti {

	/**
	 * Default constructor
	 */
	protected GWAutorizzazionePagamenti() {
	}

	/**
	 * 
	 */
	private static GWAutorizzazionePagamenti istance;

	/**
	 * @return
	 */
	public static GWAutorizzazionePagamenti getIstance() {
		if (istance==null) {
			istance = new GWAutorizzazionePagamenti();
		}
		return istance;
	}

	/**
	 * @param aPagamento 
	 * @return
	 */
	public static Boolean AutorizzaPagamento(Pagamento aPagamento) {
		// TODO implement here
		return null;
	}

}