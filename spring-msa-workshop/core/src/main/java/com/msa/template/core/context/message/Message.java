package com.msa.template.core.context.message;

import com.msa.template.core.exception.UncaughtException;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

public class Message {

	private Message() {
		throw new UnsupportedOperationException();
	}

	public static String of(final String code) {
		return getMessage(code, (Object) null);
	}

	public static String of(final String code, final Object... args) {
		return getMessage(code, args);
	}

	private static String getMessage(final String code, final Object... args) {
		try {
			return MessageConfiguration.getMessageSource().getMessage(code, args, Locale.getDefault());
		} catch (NoSuchMessageException e) {
			throw new UncaughtException(String.format("등록되지 않은 메시지입니다, %s", code));
		}
	}
}
