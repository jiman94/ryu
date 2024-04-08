package com.msa.template.order.domain.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateCommand {

	@NotBlank(message = "{order.create.NotBlank.orderCode}")
	private String orderCode;
	@NotNull(message = "{order.create.NotNull.orderUserId}")
	private Long orderUserId;
	@NotNull(message = "{order.create.NotNull.orderDate}")
	private LocalDateTime orderDate;
	@NotEmpty(message = "{order.create.NotEmpty.orderItemList}")
	@Size(max = 10, message = "{order.create.Size.orderItemList}")
	private List<OrderItem> orderItemList;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class OrderItem {
		@NotBlank(message = "{order.create.NotBlank.orderItemName}")
		private String orderItemName;
		@NotNull(message = "{order.create.NotNull.skuCode}")
		private String skuCode;
		@NotBlank(message = "{order.create.NotBlank.price}")
		private String price;
	}
}
