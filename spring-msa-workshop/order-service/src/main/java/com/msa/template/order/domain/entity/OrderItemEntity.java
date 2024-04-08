package com.msa.template.order.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;


@Table(name = "order_item")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Comment("일련번호")
	private Long id;

	@Column(name = "order_id")
	@Comment("주문 아이디")
	private Long orderId;

	@Column(name = "order_item_name")
	@Comment("주문 아이템명")
	private String orderItemName;

	@Column(name = "sku_code")
	@Comment("SKU 코드")
	private String skuCode;

	@Column(name = "price")
	@Comment("가격")
	private String price;

}
