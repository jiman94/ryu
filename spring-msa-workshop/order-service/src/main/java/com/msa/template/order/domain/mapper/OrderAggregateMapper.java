package com.msa.template.order.domain.mapper;

import com.msa.template.core.converter.Mappable;
import com.msa.template.order.domain.entity.OrderEntity;
import com.msa.template.order.domain.entity.OrderItemEntity;
import com.msa.template.order.infra.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderAggregateMapper implements Mappable<OrderEntity, OrderEntity> {

	private final OrderItemRepository orderItemRepository;

	@Override
	public OrderEntity map(OrderEntity source) {
		return this.mapAsList(List.of(source)).stream().findFirst().orElse(null);
	}

	@Override
	public List<OrderEntity> mapAsList(Collection<OrderEntity> sourceCollection) {
		if (sourceCollection == null) {
			return List.of();
		}
		Map<Long, List<OrderItemEntity>> orderItemMap = this.getOrderItemMap(sourceCollection);
		return sourceCollection.stream()
			.peek(order -> order.setOrderItemList(orderItemMap.get(order.getId())))
			.collect(Collectors.toList());
	}

	private Map<Long, List<OrderItemEntity>> getOrderItemMap(Collection<OrderEntity> sourceCollection) {
		List<Long> orderIds =
			sourceCollection.stream()
				.map(OrderEntity::getId)
				.distinct().toList();
		return orderItemRepository
			.findByOrderIdIn(orderIds)
			.stream()
			.collect(Collectors.groupingBy(OrderItemEntity::getOrderId));
	}
}
