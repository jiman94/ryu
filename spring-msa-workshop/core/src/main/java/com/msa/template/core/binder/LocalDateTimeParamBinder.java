package com.msa.template.core.binder;


import com.msa.template.core.converter.LocalDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class LocalDateTimeParamBinder implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String dateTime) {
		return LocalDateTimeConverter.from(dateTime);
	}
}
