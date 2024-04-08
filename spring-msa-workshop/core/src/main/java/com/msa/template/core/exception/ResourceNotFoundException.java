package com.msa.template.core.exception;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(@NotEmpty final String message) {
		super(message);
	}

	public ResourceNotFoundException(@NotEmpty final String message, final Throwable cause) {
		super(message, cause);
	}
}
