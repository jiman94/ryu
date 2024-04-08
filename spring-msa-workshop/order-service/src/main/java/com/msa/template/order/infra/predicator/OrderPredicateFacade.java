package com.msa.template.order.infra.predicator;

import com.msa.template.core.builder.ArrayBuilder;
import com.msa.template.order.domain.condition.OrderCondition;
import com.msa.template.order.domain.entity.QOrderEntity;
import com.msa.template.order.domain.entity.QOrderItemEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPredicateFacade {

	private final OrderPredicate orderPredicate;
	private final OrderItemPredicate orderItemPredicate;

	public BooleanExpression[] build(
		final OrderCondition condition,
		final QOrderEntity orderEntity,
		final QOrderItemEntity orderItemEntity) {

		if (condition == null) {
			return null;
		}

		return new ArrayBuilder<BooleanExpression>()
			.add(orderPredicate.build(condition, orderEntity))
			.add(orderItemPredicate.build(condition.getOwnerOrderItemCondition(), orderItemEntity))
			.build();
	}
}
