package com.msa.template.order.application.service;


import com.msa.template.core.exception.PolicyViolationException;
import com.msa.template.order.application.mapper.OrderConditionMapper;
import com.msa.template.order.application.request.OrderSearchRequestDto;
import com.msa.template.order.application.response.OrderResponseDto;
import com.msa.template.order.domain.command.OrderCreateCommand;
import com.msa.template.order.domain.condition.OrderCondition;
import com.msa.template.order.domain.enumerated.OrderStatus;
import com.msa.template.order.domain.service.OrderCudService;
import com.msa.template.order.domain.service.OrderFindService;
import com.msa.template.order.infra.api.InventoryClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@RequiredArgsConstructor
@Validated
public class OrderApplicationService {

	private final OrderFindService orderFindService;
	private final OrderCudService orderCudService;
	private final OrderConditionMapper conditionMapper;
	private final InventoryClient inventoryClient;

	public Page<OrderResponseDto> search(@Valid OrderSearchRequestDto searchDto, Pageable pageable) {
		OrderCondition condition = conditionMapper.map(searchDto);
		return orderFindService
			.findPageByCondition(condition, pageable)
			.map(OrderResponseDto::of);
	}

	public OrderResponseDto create(@Valid OrderCreateCommand command) {
		this.checkoutInventory(command);
		return OrderResponseDto.of(orderCudService.create(command));
	}

	public OrderResponseDto update(Long reviewId, @Valid OrderCreateCommand command) {
		this.checkoutInventory(command);
		return OrderResponseDto.of(orderCudService.update(reviewId, command));
	}

	public OrderResponseDto updateAsShipped(Long orderId) {
		return OrderResponseDto.of(orderCudService.updateOrderStatus(orderId, OrderStatus.SHIPPED));
	}

	public void delete(Long reviewId) {
		orderCudService.delete(reviewId);
	}

	private void checkoutInventory(OrderCreateCommand command) {
		command.getOrderItemList().stream()
			.map(orderItem -> {
				try {
					return inventoryClient.getInventory(orderItem.getSkuCode());
				} catch (Exception e) {
					throw new PolicyViolationException("재고 조회에 실패했습니다.");
				}
			})
			.filter(response -> response.getData().getStock() < 1)
			.findAny()
			.ifPresent(response -> {
				throw new PolicyViolationException("재고가 없습니다.");
			});
	}
}
