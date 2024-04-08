package com.msa.template.order.infra.repository;

import com.msa.template.order.config.database.QueryDslRepositorySupport;
import com.msa.template.order.domain.condition.OrderCondition;
import com.msa.template.order.domain.entity.OrderEntity;
import com.msa.template.order.domain.entity.QOrderEntity;
import com.msa.template.order.domain.entity.QOrderItemEntity;
import com.msa.template.order.infra.predicator.OrderPredicateFacade;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class OrderRepositoryImpl extends QueryDslRepositorySupport implements OrderRepositoryCustom {

	private final QOrderEntity order = QOrderEntity.orderEntity;
	private final QOrderItemEntity orderItem = QOrderItemEntity.orderItemEntity;

	private final OrderPredicateFacade orderPredicateFacade;

	public OrderRepositoryImpl(OrderPredicateFacade orderPredicateFacade) {
		super(OrderEntity.class);
		this.orderPredicateFacade = orderPredicateFacade;
	}

	@Override
	public List<OrderEntity> findListByCondition(OrderCondition condition) {
		return getBaseQueryByCondition(condition).fetch();
	}

	@Override
	public Page<OrderEntity> findPageByCondition(OrderCondition condition, Pageable pageable) {
		assert getQuerydsl() != null;
		JPQLQuery<OrderEntity> baseQuery = getBaseQueryByCondition(condition);
		JPQLQuery<OrderEntity> pageQuery = getQuerydsl().applyPagination(pageable, baseQuery);
		return new PageImpl<>(pageQuery.fetch(), pageable, baseQuery.fetchCount());
	}

	private JPQLQuery<OrderEntity> getBaseQueryByCondition(OrderCondition condition) {
		return from(order)
			.leftJoin(orderItem)
			.on(orderItem.orderId.eq(order.id))
			.fetchJoin()
			.where(orderPredicateFacade.build(condition, order, orderItem))
			.groupBy(order.id);
	}
}
