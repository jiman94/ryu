package com.msa.template.core.exception;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class PolicyViolationException extends RuntimeException {

	public PolicyViolationException(@NotEmpty final String message) {
		super(message);
	}

	public PolicyViolationException(@NotEmpty final String message, final Throwable cause) {
		super(message, cause);
	}
}
