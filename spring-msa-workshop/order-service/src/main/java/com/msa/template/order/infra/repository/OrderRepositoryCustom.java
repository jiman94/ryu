package com.msa.template.order.infra.repository;

import com.msa.template.order.domain.condition.OrderCondition;
import com.msa.template.order.domain.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {

	List<OrderEntity> findListByCondition(final OrderCondition condition);

	Page<OrderEntity> findPageByCondition(
		final OrderCondition condition, final Pageable pageable);
}
