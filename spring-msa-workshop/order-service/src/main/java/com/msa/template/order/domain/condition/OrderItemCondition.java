package com.msa.template.order.domain.condition;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class OrderItemCondition {

	private String orderItemName;
	private String skuCode;
	private String price;
}
