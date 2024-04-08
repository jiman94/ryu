package com.msa.template.core.converter;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface Mappable<S, D> extends SingleMappable<S, D>, CollectionMappable<S, D> {

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

	/**
	 * Iterable 객체의 정보를 다른 객체 List 정보에 연결합니다.
	 *
	 * @param sourceIterable Iterable 형태의 데이터 출처 객체
	 * @return 연결한 결과 목록 객체를 반환합니다.
	 */
	default List<D> mapAsList(Iterable<S> sourceIterable) {
		return mapAsList(
			StreamSupport.stream(sourceIterable.spliterator(), false).collect(Collectors.toList()));
	}

	/**
	 * Collection 객체의 정보를 다른 객체 List 객체 정보에 연결합니다.
	 *
	 * @param sourceCollection Collection 형태의 데이터 출처 객체
	 * @return 연결한 결과 목록 객체를 반환합니다.
	 */
	default List<D> mapAsList(Collection<S> sourceCollection) {
		return CollectionUtils.emptyIfNull(sourceCollection).stream()
			.map(this::map)
			.collect(Collectors.toList());
	}

	/**
	 * Collection 객체의 정보를 다른 객체 Set 객체 정보에 연결합니다.
	 *
	 * @param sourceCollection Collection 형태의 데이터 출처 객체
	 * @return 연결한 결과 목록 객체를 반환합니다.
	 */
	default Set<D> mapAsSet(Collection<S> sourceCollection) {
		return CollectionUtils.emptyIfNull(sourceCollection).stream()
			.map(this::map)
			.collect(Collectors.toSet());
	}

	/**
	 * Page 객체의 정보를 다른 Page 객체 정보에 연결합니다.
	 *
	 * @param sourcePage Page 형태의 데이터 출처 객체
	 * @return 연결한 결과 Page 객체를 반환합니다.
	 */
	default Page<D> mapAsPage(Page<S> sourcePage) {
		return new PageImpl<>(
			mapAsList(CollectionUtils.emptyIfNull(sourcePage.getContent())),
			sourcePage.getPageable(),
			sourcePage.getTotalElements());
	}

	default <S, D> D getOrDefault(S source, Function<S, D> function, D defaultValue) {
		if (source == null) {
			return defaultValue;
		}
		return function.apply(source);
	}

	default <S, D> D getOrNull(S source, Function<S, D> function) {
		return getOrDefault(source, function, null);
	}
}
