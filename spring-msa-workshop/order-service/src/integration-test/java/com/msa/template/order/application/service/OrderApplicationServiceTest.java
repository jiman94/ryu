
package com.msa.template.order.application.service;

import com.msa.template.order.IntegrationSupport;
import com.msa.template.order.application.response.OrderResponseDto;
import com.msa.template.order.config.database.DomainTransactional;
import com.msa.template.order.domain.command.OrderCreateCommand;
import com.msa.template.order.infra.api.InventoryClient;
import com.msa.template.order.infra.api.response.InventoryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

public class OrderApplicationServiceTest extends IntegrationSupport {

	@Autowired
	private OrderApplicationService orderApplicationService;

	@MockBean
	private InventoryClient inventoryClient;

	@BeforeEach
	public void setUp() {
		Mockito.when(inventoryClient.getInventory(Mockito.anyString())).thenReturn(
			InventoryResponse.builder()
				.success(true)
				.data(InventoryResponse.Data.builder()
					.skuCode("skuCode")
					.stock(100)
					.build())
				.build()
		);
	}

	@Test
	@DomainTransactional
	public void 고객이_딸기와_사과_주문을_성공한다() {
		OrderResponseDto orderResponseDto = orderApplicationService.create(OrderCreateCommand.builder()
			.orderCode("orderCode")
			.orderUserId(1L)
			.orderDate(LocalDateTime.now())
			.orderItemList(List.of(
				OrderCreateCommand.OrderItem.builder()
					.orderItemName("딸기")
					.skuCode("skuCode1")
					.price("1000")
					.build(),
				OrderCreateCommand.OrderItem.builder()
					.orderItemName("사과")
					.skuCode("skuCode2")
					.price("2000")
					.build()
			))
			.build());
		Assertions.assertEquals("orderCode", orderResponseDto.getOrderCode());
		Assertions.assertTrue(orderResponseDto.getOrderItemList().stream()
			.anyMatch(orderItemResponseDto -> orderItemResponseDto.getOrderItemName().equals("딸기")));
	}


	@Test
	@DomainTransactional
	public void 배송중_주문은_주문항목변경이_불가능하다() {
		// given : 주문 추가
		OrderResponseDto orderResponseDto = orderApplicationService.create(OrderCreateCommand.builder()
			.orderCode("orderCode")
			.orderUserId(1L)
			.orderDate(LocalDateTime.now())
			.orderItemList(List.of(
				OrderCreateCommand.OrderItem.builder()
					.orderItemName("딸기")
					.skuCode("skuCode1")
					.price("1000")
					.build(),
				OrderCreateCommand.OrderItem.builder()
					.orderItemName("사과")
					.skuCode("skuCode2")
					.price("2000")
					.build()
			))
			.build());

		// given : 배송중으로 변경
		orderApplicationService.updateAsShipped(orderResponseDto.getId());

		// when, then : 주문항목 변경시 예외발생
		Assertions.assertThrows(RuntimeException.class, () -> orderApplicationService.update(orderResponseDto.getId(), OrderCreateCommand.builder()
			.orderCode("orderCode")
			.orderUserId(1L)
			.orderDate(LocalDateTime.now())
			.orderItemList(List.of(
				OrderCreateCommand.OrderItem.builder()
					.orderItemName("바나나")
					.skuCode("skuCode3")
					.price("1000")
					.build(),
				OrderCreateCommand.OrderItem.builder()
					.orderItemName("오렌지")
					.skuCode("skuCode4")
					.price("2000")
					.build()
			))
			.build()));
	}
}
