package com.msa.template.inventory;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {

	private Long id;
	private String skuCode;
	private Integer stock;

	public static InventoryResponseDto of(InventoryEntity inventoryEntity) {
		return InventoryResponseDto.builder()
			.id(inventoryEntity.getId())
			.skuCode(inventoryEntity.getSkuCode())
			.stock(inventoryEntity.getStock())
			.build();
	}
}
