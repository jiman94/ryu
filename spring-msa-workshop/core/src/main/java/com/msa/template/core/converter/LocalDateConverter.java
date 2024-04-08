package com.msa.template.core.converter;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Slf4j
public class LocalDateConverter {

	public static final DateTimeFormatter DATE_FORMAT = ofPattern("yyyy-MM-dd");

	private LocalDateConverter() {
		throw new UnsupportedOperationException();
	}

	public static String to(final LocalDate localDate) {
		return LocalDateConverter.to(localDate, LocalDateConverter.buildFormatter());
	}

	public static String to(final LocalDate localDate, final DateTimeFormatter formatter) {
		if (localDate == null) {
			return null;
		}

		return localDate.format(formatter);
	}

	public static LocalDate from(final String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		return LocalDate.parse(str, LocalDateConverter.buildFormatter());
	}

	private static DateTimeFormatter buildFormatter() {
		return DATE_FORMAT;
	}
}
