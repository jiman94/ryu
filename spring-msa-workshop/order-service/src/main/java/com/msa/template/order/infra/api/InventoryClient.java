package com.msa.template.order.infra.api;

import com.msa.template.order.config.feign.FeignClientConfig;
import com.msa.template.order.infra.api.response.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
	name = "inventory-api",
	url = "${inventory.url}",
	configuration = FeignClientConfig.class)
public interface InventoryClient {

	@GetMapping("/api/inventory/{skuCode}")
	InventoryResponse getInventory(
		@PathVariable(value = "skuCode") String skuCode);
}
