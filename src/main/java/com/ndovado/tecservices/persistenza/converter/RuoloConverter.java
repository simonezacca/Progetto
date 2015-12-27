package com.ndovado.tecservices.persistenza.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Gestore;
import com.ndovado.dominio.core.Locatario;

@Converter
public class RuoloConverter implements AttributeConverter<ARuolo, Integer> {

 /**
  * Converte un'istanza della classe ARuolo
  * con un valore intero, 0 per locatario, 1 per gestore
  */
 
 public Integer convertToDatabaseColumn(ARuolo ruolo) {
	 if (ruolo instanceof Locatario)
		return 0;
	 else if (ruolo instanceof Gestore)  
		 return 1;
	 return 0;
 }

 /**
  * Converte un valore intero nella corrispettiva istanza ARuolo
  * 0 per locatario, 1 per gestore
  */
 public ARuolo convertToEntityAttribute(Integer codiceRuolo) {
	 if (codiceRuolo == 0) // codice locatario
		 return ARuolo.getRuoloLocatario();
	 else if (codiceRuolo==1)  // codice gestore
		 return ARuolo.getRuoloGestore();
	 return null;
 }
}