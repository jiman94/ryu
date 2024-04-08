package com.msa.template.order.domain.service;

import com.msa.template.core.exception.ResourceNotFoundException;
import com.msa.template.order.config.database.DomainTransactional;
import com.msa.template.order.domain.condition.OrderCondition;
import com.msa.template.order.domain.entity.OrderEntity;
import com.msa.template.order.domain.mapper.OrderAggregateMapper;
import com.msa.template.order.infra.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@DomainTransactional(readOnly = true)
@RequiredArgsConstructor
public class OrderFindService {

	private final OrderRepository orderRepository;
	private final OrderAggregateMapper aggregateMapper;

	public OrderEntity findById(Long orderId) {
		OrderEntity entity = orderRepository.findById(orderId).orElseThrow(ResourceNotFoundException::new);
		return aggregateMapper.map(entity);
	}

	public Collection<OrderEntity> findListByCondition(OrderCondition condition) {
		List<OrderEntity> entities = orderRepository.findListByCondition(condition);
		return aggregateMapper.mapAsList(entities);
	}

	public Page<OrderEntity> findPageByCondition(OrderCondition condition, Pageable pageable) {
		Page<OrderEntity> page = orderRepository.findPageByCondition(condition, pageable);
		return aggregateMapper.mapAsPage(page);
	}
}
