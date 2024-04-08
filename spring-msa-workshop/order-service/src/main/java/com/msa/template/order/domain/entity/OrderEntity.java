package com.msa.template.order.domain.entity;


import com.msa.template.order.domain.enumerated.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.List;


@Table(name = "`order`")
@Entity
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Comment("일련번호")
	private Long id;

	@Column(name = "order_code")
	@Comment("주문코드")
	private String orderCode;

	@Column(name = "order_user_id")
	@Comment("주문자아이디")
	private Long orderUserId;

	@Column(name = "order_date")
	@Comment("주문일자")
	private LocalDateTime orderDate;

	@Column(name = "order_status")
	@Enumerated(EnumType.STRING)
	@Comment("주문상태")
	private OrderStatus orderStatus;

	@Transient
	private List<OrderItemEntity> orderItemList;
}
