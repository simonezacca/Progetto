package com.ndovado.dominio.prenotazioni;

import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 
 */

@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public interface IPrenotabile{

	/**
	 * @return
	 */
	public Float getPrezzo();

	/**
	 * @return
	 */
	public String getNomeOggettoPrenotabile();

}