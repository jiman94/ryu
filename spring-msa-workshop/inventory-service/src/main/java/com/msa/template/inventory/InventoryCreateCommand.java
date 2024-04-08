package com.msa.template.inventory;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryCreateCommand {

	private String skuCode;
	private Integer stock;
}
