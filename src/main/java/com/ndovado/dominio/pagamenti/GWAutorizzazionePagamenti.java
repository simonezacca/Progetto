package com.ndovado.dominio.pagamenti;

import java.math.BigInteger;
import java.util.Random;

import org.joda.time.LocalDateTime;

/**
 * 
 */
public class GWAutorizzazionePagamenti {

	
	Random generator = new Random();
	/**
	 * Default constructor
	 */
	protected GWAutorizzazionePagamenti() {
	}

	/**
	 * 
	 */
	private static volatile GWAutorizzazionePagamenti istance;

	/**
	 * @return
	 */
	public static GWAutorizzazionePagamenti getIstance() {
		if (istance==null) {
			synchronized (GWAutorizzazionePagamenti.class) {
				if (istance==null)
					istance = new GWAutorizzazionePagamenti();
			}
		}
		return istance;
	}

	public synchronized Pagamento creaRichiestaAuthPagamento(Pagamento pToAuth) {
		if (isAuthorizable()) {
			pToAuth.setAutorizzato(true);
			pToAuth.setDataOraPagamento(new LocalDateTime());
			pToAuth.setIdTransazione(getIdTransazione());
		} else {
			pToAuth.setAutorizzato(false);
		}
		return pToAuth;
	}
	
	private synchronized Boolean isAuthorizable() {
		Boolean result = false;
		Integer num = generator.nextInt(100);
		if (num>=1) {
			result = true;
		}
		return result;
	}
	
	private synchronized String getIdTransazione() {
		 return new BigInteger(130, generator).toString(32);
	}

}