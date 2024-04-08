package com.msa.template.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constraints {

	public static final Long LONG_MINUS_1 = -1L;
	public static final Long LONG_ZERO = 0L;
	public static final Long LONG_1 = 1L;
	/** 쿼리 실행 단위 (IN 절 사용 시 항목 최대 수 제한하는 용도로 사용) */
	public static final int QUERY_IN_UNIT = 2000;
	/** 트랜잭션 실행 단위 */
	public static final int TRANSACTION_IN_UNIT = 500;

	public static final int DEFAULT_PAGE_SIZE = 20;
	public static final int MAX_PAGE_SIZE = 1000;
}
