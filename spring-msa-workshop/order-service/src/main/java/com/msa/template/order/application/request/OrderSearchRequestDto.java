package com.msa.template.order.application.request;


import com.msa.template.order.domain.enumerated.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class OrderSearchRequestDto {

	private String orderCode;
	private Long orderUserId;
	private LocalDateTime orderDateStart;
	private LocalDateTime orderDateEnd;
	private OrderStatus orderStatus;
	private String orderItemName;
	private String skuCode;
	private String price;
}
