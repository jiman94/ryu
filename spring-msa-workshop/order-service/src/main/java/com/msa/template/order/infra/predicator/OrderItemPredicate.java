package com.msa.template.order.infra.predicator;

import com.msa.template.core.persistence.DynamicExpression;
import com.msa.template.order.domain.condition.OrderItemCondition;
import com.msa.template.order.domain.entity.QOrderItemEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class OrderItemPredicate
	implements DynamicExpression<OrderItemCondition, QOrderItemEntity> {

	@Override
	public BooleanExpression[] build(
		final OrderItemCondition condition, final QOrderItemEntity entity) {
		if (condition == null) {
			return null;
		}

		return new BooleanExpression[]{
			eqOrderItemName(condition.getOrderItemName(), entity),
			eqSkuCode(condition.getSkuCode(), entity),
			eqPrice(condition.getPrice(), entity)
		};
	}

	private BooleanExpression eqOrderItemName(final String orderItemName, final QOrderItemEntity entity) {
		return StringUtils.hasText(orderItemName) ? entity.orderItemName.eq(orderItemName) : null;
	}

	private BooleanExpression eqSkuCode(final String skuCode, final QOrderItemEntity entity) {
		return Objects.nonNull(skuCode) ? entity.skuCode.eq(skuCode) : null;
	}

	private BooleanExpression eqPrice(final String price, final QOrderItemEntity entity) {
		return StringUtils.hasText(price) ? entity.price.eq(price) : null;
	}
}
