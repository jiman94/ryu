package com.msa.template.order.infra.predicator;

import com.msa.template.core.persistence.DynamicExpression;
import com.msa.template.order.domain.condition.OrderCondition;
import com.msa.template.order.domain.entity.QOrderEntity;
import com.msa.template.order.domain.enumerated.OrderStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Component
public class OrderPredicate
	implements DynamicExpression<OrderCondition, QOrderEntity> {

	@Override
	public BooleanExpression[] build(
		final OrderCondition condition, final QOrderEntity entity) {
		if (condition == null) {
			return null;
		}

		return new BooleanExpression[]{
			inIds(condition.getIds(), entity),
			eqOrderCode(condition.getOrderCode(), entity),
			eqOrderUserId(condition.getOrderUserId(), entity),
			eqOrderDate(condition.getOrderDateStart(), condition.getOrderDateEnd(), entity),
			eqOrderStatus(condition.getOrderStatus(), entity),
			entity.deleted.isFalse()
		};
	}

	private BooleanExpression inIds(final Collection<Long> ids, final QOrderEntity entity) {
		return Objects.isNull(ids) ? null : entity.id.in(ids);
	}

	private BooleanExpression eqOrderCode(final String orderCode, final QOrderEntity entity) {
		return StringUtils.hasText(orderCode) ? entity.orderCode.eq(orderCode) : null;
	}

	private BooleanExpression eqOrderUserId(final Long orderUserId, final QOrderEntity entity) {
		return Objects.nonNull(orderUserId) ? entity.orderUserId.eq(orderUserId) : null;
	}

	private BooleanExpression eqOrderDate(final LocalDateTime orderDateStart, final LocalDateTime orderDateEnd, final QOrderEntity entity) {
		// range compare
		if (Objects.nonNull(orderDateStart) && Objects.nonNull(orderDateEnd)) {
			return entity.orderDate.between(orderDateStart, orderDateEnd);
		}
		// greater than or equal
		if (Objects.nonNull(orderDateStart)) {
			return entity.orderDate.goe(orderDateStart);
		}
		// less than or equal
		if (Objects.nonNull(orderDateEnd)) {
			return entity.orderDate.loe(orderDateEnd);
		}
		return null;
	}

	private BooleanExpression eqOrderStatus(final OrderStatus orderStatus, final QOrderEntity entity) {
		return Objects.nonNull(orderStatus) ? entity.orderStatus.eq(orderStatus) : null;
	}
}
