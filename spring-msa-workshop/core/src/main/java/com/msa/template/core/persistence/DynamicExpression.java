package com.msa.template.core.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface DynamicExpression<C, Q> {

	BooleanExpression[] build(C condition, Q entity);
}
