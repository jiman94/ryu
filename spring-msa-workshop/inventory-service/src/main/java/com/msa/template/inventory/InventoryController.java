package com.msa.template.inventory;


import com.msa.template.core.exception.ResourceNotFoundException;
import com.msa.template.core.web.api.ApiResponse;
import com.msa.template.core.web.api.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryRepository inventoryRepository;


	@GetMapping("{skuCode}")
	public ApiResponse<InventoryResponseDto> get(
		@PathVariable String skuCode) {
		InventoryEntity inventoryEntity = inventoryRepository.findBySkuCode(skuCode);
		Optional.ofNullable(inventoryEntity).orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
		return ApiResponseGenerator.success(InventoryResponseDto.of(inventoryEntity));
	}

	@PostMapping("")
	public ApiResponse<InventoryResponseDto> create(@RequestBody InventoryCreateCommand command) {
		InventoryEntity saved = inventoryRepository.save(InventoryEntity.builder()
			.skuCode(command.getSkuCode())
			.stock(command.getStock())
			.build());
		return ApiResponseGenerator.success(InventoryResponseDto.of(saved));
	}

	@PutMapping("{skuCode}")
	public ApiResponse<InventoryResponseDto> update(@PathVariable String skuCode, @RequestBody InventoryCreateCommand command) {
		InventoryEntity inventoryEntity = inventoryRepository.findBySkuCode(skuCode);
		Optional.ofNullable(inventoryEntity).orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
		inventoryEntity.setStock(command.getStock());
		InventoryEntity saved = inventoryRepository.save(inventoryEntity);
		return ApiResponseGenerator.success(InventoryResponseDto.of(saved));
	}

	@DeleteMapping("{skuCode}")
	public ApiResponse<Void> delete(@PathVariable String skuCode) {
		InventoryEntity inventoryEntity = inventoryRepository.findBySkuCode(skuCode);
		Optional.ofNullable(inventoryEntity).orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
		inventoryRepository.delete(inventoryEntity);
		return ApiResponseGenerator.success();
	}
}
