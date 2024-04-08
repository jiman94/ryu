package com.msa.template.core.converter;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LocalTimeConverter {

	private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

	private LocalTimeConverter() {
		throw new UnsupportedOperationException();
	}

	public static String to(final LocalTime localTime) {
		return LocalTimeConverter.to(localTime, DEFAULT_FORMATTER);
	}

	public static String to(final LocalTime localTime, final DateTimeFormatter formatter) {
		if (localTime == null) {
			return null;
		}

		return localTime.format(formatter);
	}

	public static LocalTime from(final String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}

		return LocalTime.parse(str, DEFAULT_FORMATTER);
	}
}
