package com.msa.template.order.domain.condition;

import com.msa.template.order.domain.enumerated.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class OrderCondition {

	private Collection<Long> ids;
	private String orderCode;
	private Long orderUserId;
	private LocalDateTime orderDateStart;
	private LocalDateTime orderDateEnd;
	private OrderStatus orderStatus;
	private OrderItemCondition ownerOrderItemCondition;
}
