package com.ndovado.tecservices.mappers.converters;

import org.dozer.DozerConverter;
import org.joda.time.LocalDate;

public class LocalDateConveter extends DozerConverter<LocalDate, LocalDate> {

	public LocalDateConveter() {
	        super(LocalDate.class, LocalDate.class);
	    }

	@Override
	public LocalDate convertTo(final LocalDate source, final LocalDate destination) {
		if (source == null) {
			return null;
		}
		return new LocalDate(source);
	}

	@Override
	public LocalDate convertFrom(final LocalDate source, final LocalDate destination) {
		if (source == null) {
			return null;
		}
		return new LocalDate(source);
	}

}
