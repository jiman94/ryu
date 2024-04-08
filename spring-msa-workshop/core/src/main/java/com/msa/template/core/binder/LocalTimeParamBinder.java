package com.msa.template.core.binder;


import com.msa.template.core.converter.LocalTimeConverter;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

public class LocalTimeParamBinder implements Converter<String, LocalTime> {

	@Override
	public LocalTime convert(final String time) {
		return LocalTimeConverter.from(time);
	}
}
