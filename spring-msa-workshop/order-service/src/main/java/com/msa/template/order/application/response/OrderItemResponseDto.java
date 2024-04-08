package com.msa.template.order.application.response;

import com.msa.template.order.domain.entity.OrderItemEntity;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {

	private Long id;
	private Long orderId;
	private String orderItemName;
	private String skuCode;
	private String price;

	public static OrderItemResponseDto of(OrderItemEntity orderItemEntity) {
		return OrderItemResponseDto.builder()
			.id(orderItemEntity.getId())
			.orderId(orderItemEntity.getOrderId())
			.orderItemName(orderItemEntity.getOrderItemName())
			.skuCode(orderItemEntity.getSkuCode())
			.price(orderItemEntity.getPrice())
			.build();
	}
}
