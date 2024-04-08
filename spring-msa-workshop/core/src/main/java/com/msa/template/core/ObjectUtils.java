package com.msa.template.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtils {

	public static <S, D> D getOrNull(S source, Function<S, D> function) {
		return getOrDefault(source, function, null);
	}

	public static <S, D> D getOrDefault(S source, Function<S, D> function, D defaultValue) {
		if (source == null) {
			return defaultValue;
		}
		return function.apply(source);
	}
}
