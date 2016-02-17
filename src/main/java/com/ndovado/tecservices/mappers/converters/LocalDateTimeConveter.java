package com.ndovado.tecservices.mappers.converters;

import org.dozer.DozerConverter;
import org.joda.time.LocalDateTime;

public class LocalDateTimeConveter extends DozerConverter<LocalDateTime, LocalDateTime> {

	public LocalDateTimeConveter() {
		super(LocalDateTime.class, LocalDateTime.class);
	}
	
	@Override
	public LocalDateTime convertTo(LocalDateTime source, LocalDateTime destination) {
		if (source == null) {
			return null;
		}
		return new LocalDateTime(source);
	}

	@Override
	public LocalDateTime convertFrom(LocalDateTime source, LocalDateTime destination) {
		if (source == null) {
			return null;
		}
		return new LocalDateTime(source);
	}

}
