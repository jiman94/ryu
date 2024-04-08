package com.msa.template.core.converter;

import java.util.Optional;

public interface SingleMappable<S, D> {

	/**
	 * 객체의 속성을 다른 객체의 속성에 연결합니다.
	 *
	 * @param source 데이터 출처 객체
	 * @return 연결한 결과 객체를 반환합니다.
	 */
	D map(S source);

	/**
	 * Optional 객체의 속성을 다른 객체의 속성에 연결합니다.
	 *
	 * @param sourceOptional Optional 형태의 데이터 출처 객체
	 * @return 연결한 결과 객체를 반환합니다.
	 */
	default D map(Optional<S> sourceOptional) {
		return this.map(sourceOptional.orElse(null));
	}
}
