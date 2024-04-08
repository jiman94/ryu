package com.msa.template.core.exception;


import jakarta.validation.constraints.NotEmpty;

public class UncaughtException extends RuntimeException {

public UncaughtException(final Throwable cause) {
	super(cause.getMessage(), cause);
}

public UncaughtException(@NotEmpty final String message) {
	super(message);
}

public UncaughtException(@NotEmpty final String message, final Throwable cause) {
	super(message, cause);
}
}
