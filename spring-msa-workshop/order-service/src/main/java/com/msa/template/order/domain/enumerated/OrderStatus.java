package com.msa.template.order.domain.enumerated;

public enum OrderStatus {

	PENDING("주문접수"),
	PROCESSING("처리중"),
	CONFIRMED("확인완료"),
	SHIPPED("배송중"),
	DELIVERED("배송완료"),
	CANCELLED("주문취소"),
	RETURNED("반품"),
	REFUNDED("환불완료"),
	FAILED("주문실패");

	OrderStatus(String desc) {
		this.desc = desc;
	}

	private final String desc;
}
