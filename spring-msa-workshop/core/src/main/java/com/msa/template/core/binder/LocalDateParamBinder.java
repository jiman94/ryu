package com.msa.template.core.binder;


import com.msa.template.core.converter.LocalDateConverter;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDateParamBinder implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(final String date) {
		return LocalDateConverter.from(date);
	}
}
