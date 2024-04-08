package com.msa.template.core.validation;

import jakarta.validation.*;

import java.util.Set;

public abstract class SelfValidatable<T> {

private final Validator validator;

public SelfValidatable() {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator = factory.getValidator();
}

protected abstract void validate();

protected void validateSelf() {
	Set<ConstraintViolation<T>> violations = validator.validate((T) this);
	if (!violations.isEmpty()) {
	throw new ConstraintViolationException(violations);
	}
}
}
