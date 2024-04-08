package com.msa.template.order.domain.service;

import com.msa.template.core.exception.PolicyViolationException;
import com.msa.template.core.exception.ResourceNotFoundException;
import com.msa.template.order.config.database.DomainTransactional;
import com.msa.template.order.domain.command.OrderCreateCommand;
import com.msa.template.order.domain.entity.OrderEntity;
import com.msa.template.order.domain.entity.OrderItemEntity;
import com.msa.template.order.domain.enumerated.OrderStatus;
import com.msa.template.order.infra.api.InventoryClient;
import com.msa.template.order.infra.repository.OrderItemRepository;
import com.msa.template.order.infra.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@DomainTransactional
@RequiredArgsConstructor
public class OrderCudService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final OrderFindService orderFindService;

	private final InventoryClient inventoryClient;

	public OrderEntity create(OrderCreateCommand command) {
		OrderEntity createdOrder = orderRepository.save(
			OrderEntity.builder()
				.orderCode(command.getOrderCode())
				.orderUserId(command.getOrderUserId())
				.orderDate(command.getOrderDate())
				.orderStatus(OrderStatus.PENDING)
				.build()
		);

		Long orderId = createdOrder.getId();
		command.getOrderItemList().forEach(orderItem ->
			orderItemRepository.save(
				OrderItemEntity.builder()
					.orderId(orderId)
					.orderItemName(orderItem.getOrderItemName())
					.skuCode(orderItem.getSkuCode())
					.price(orderItem.getPrice())
					.build()
			));
		return orderFindService.findById(orderId);
	}

	public OrderEntity update(Long orderId, OrderCreateCommand command) {
		// update
		OrderEntity existOrder = orderRepository.findById(orderId).orElseThrow(ResourceNotFoundException::new);
		if (existOrder.getOrderStatus() != OrderStatus.PENDING) {
			throw new PolicyViolationException("주문접수중일 때만 수정이 가능합니다.");
		}

		orderRepository.save(
			existOrder.toBuilder()
				.orderCode(command.getOrderCode())
				.orderUserId(command.getOrderUserId())
				.orderDate(command.getOrderDate())
				.build()
		);

		//delete all order items
		orderItemRepository.deleteByOrderId(orderId);

		//re crate order items
		command.getOrderItemList().forEach(orderItem ->
			orderItemRepository.save(
				OrderItemEntity.builder()
					.orderId(orderId)
					.orderItemName(orderItem.getOrderItemName())
					.skuCode(orderItem.getSkuCode())
					.price(orderItem.getPrice())
					.build()
			));
		return orderFindService.findById(orderId);
	}

	public OrderEntity updateOrderStatus(Long orderId, OrderStatus orderStatus) {
		// update order status
		OrderEntity existOrder = orderRepository.findById(orderId).orElseThrow(ResourceNotFoundException::new);
		existOrder.setOrderStatus(orderStatus);
		orderRepository.save(existOrder);
		return orderFindService.findById(orderId);
	}

	public void delete(Long orderId) {
		// update deleted column and save
		OrderEntity existOrder = orderRepository.findById(orderId).orElseThrow(ResourceNotFoundException::new);
		existOrder.setDeleted(true);
		orderRepository.save(existOrder);
	}
}
