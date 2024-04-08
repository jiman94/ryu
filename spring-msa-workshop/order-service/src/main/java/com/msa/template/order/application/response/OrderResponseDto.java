package com.msa.template.order.application.response;

import com.msa.template.order.domain.entity.OrderEntity;
import com.msa.template.order.domain.enumerated.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

	private Long id;
	private String orderCode;
	private Long orderUserId;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	protected Long createUser;
	protected LocalDateTime createDate;
	protected Long updateUser;
	protected LocalDateTime updateDate;
	private List<OrderItemResponseDto> orderItemList;

	public static OrderResponseDto of(OrderEntity orderEntity) {
		return OrderResponseDto.builder()
			.id(orderEntity.getId())
			.orderCode(orderEntity.getOrderCode())
			.orderUserId(orderEntity.getOrderUserId())
			.orderDate(orderEntity.getOrderDate())
			.orderStatus(orderEntity.getOrderStatus())
			.createUser(orderEntity.getCreateUser())
			.createDate(orderEntity.getCreateDate())
			.updateUser(orderEntity.getUpdateUser())
			.updateDate(orderEntity.getUpdateDate())
			.orderItemList(
				orderEntity.getOrderItemList().stream()
					.map(OrderItemResponseDto::of)
					.toList())
			.build();
	}
}
