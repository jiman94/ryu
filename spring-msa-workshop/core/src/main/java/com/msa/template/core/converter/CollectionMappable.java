package com.msa.template.core.converter;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface CollectionMappable<S, D> {

	/**
	 * Collection 객체의 정보를 다른 객체 List 객체 정보에 연결합니다.
	 *
	 * @param sourceCollection Collection 형태의 데이터 출처 객체
	 * @return 연결한 결과 목록 객체를 반환합니다.
	 */
	List<D> mapAsList(Collection<S> sourceCollection);

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
}
