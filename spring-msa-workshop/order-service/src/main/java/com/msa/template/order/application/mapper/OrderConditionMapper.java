package com.msa.template.order.application.mapper;

import com.msa.template.order.application.request.OrderSearchRequestDto;
import com.msa.template.order.domain.condition.OrderCondition;
import com.msa.template.order.domain.condition.OrderItemCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderConditionMapper {

	public OrderCondition map(OrderSearchRequestDto source) {
		return OrderCondition.builder()
			.orderCode(source.getOrderCode())
			.orderUserId(source.getOrderUserId())
			.orderDateStart(source.getOrderDateStart())
			.orderDateEnd(source.getOrderDateEnd())
			.orderStatus(source.getOrderStatus())
			.ownerOrderItemCondition(
				OrderItemCondition.builder()
					.orderItemName(source.getOrderItemName())
					.skuCode(source.getSkuCode())
					.price(source.getPrice())
					.build())
			.build();
	}
}
