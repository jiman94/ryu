package com.msa.template.order.infra.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

	private Boolean success;
	private Data data;

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Data {
		private Long id;
		private String skuCode;
		private Integer stock;
	}
}
