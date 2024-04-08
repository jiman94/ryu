package com.msa.template.core.converter;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Slf4j
public class LocalDateTimeConverter {

	public static final DateTimeFormatter DATE_TIME_FORMAT = ofPattern("yyyy-MM-dd HH:mm:ss");

	private LocalDateTimeConverter() {
		throw new UnsupportedOperationException();
	}

	public static String to(final LocalDateTime localDateTime) {
		return LocalDateTimeConverter.to(localDateTime, LocalDateTimeConverter.buildFormatter());
	}

	public static String to(final LocalDateTime localDateTime, final DateTimeFormatter formatter) {
		if (localDateTime == null) {
			return null;
		}

		return localDateTime.format(formatter);
	}

	public static LocalDateTime from(final String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		return LocalDateTime.parse(str, LocalDateTimeConverter.buildFormatter());
	}

	private static DateTimeFormatter buildFormatter() {
		return DATE_TIME_FORMAT;
	}
}
