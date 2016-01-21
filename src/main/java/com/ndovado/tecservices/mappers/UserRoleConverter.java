package com.ndovado.tecservices.mappers;

import org.dozer.DozerConverter;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Gestore;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.helpers.core.TipoUtente;

public class UserRoleConverter extends DozerConverter<ARuolo, TipoUtente> {

	public UserRoleConverter() {
		super(ARuolo.class, TipoUtente.class);
	}

	@Override
	public ARuolo convertFrom(TipoUtente arg0, ARuolo arg1) {
		// arg0 -> arg1
		if (arg0.equals(TipoUtente.GESTORE)) {
			arg1 = ARuolo.getRuoloGestore();
		} else if (arg0.equals(TipoUtente.LOCATARIO)) {
			arg1 = ARuolo.getRuoloLocatario();
		} else 
			throw new IllegalStateException("Unknown value!");
		return arg1;
		
	}

	@Override
	public TipoUtente convertTo(ARuolo arg0, TipoUtente arg1) {
		// arg0 -> arg1
		if (arg0 instanceof Gestore) {
			arg1 = TipoUtente.GESTORE;
		} else if (arg0 instanceof Locatario) {
			arg1 = TipoUtente.LOCATARIO;
		} else 
			throw new IllegalStateException("Unknown value!");
		return arg1;
	}
	
	
}
