package com.msa.template.inventory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

	InventoryEntity findBySkuCode(String skuCode);
}
