package com.msa.template.core.exception;


import jakarta.validation.constraints.NotEmpty;

public class UnauthorizedException extends RuntimeException {

public UnauthorizedException(@NotEmpty final String message) {
	super(message);
}

public UnauthorizedException(@NotEmpty final String message, final Throwable cause) {
	super(message, cause);
}
}
