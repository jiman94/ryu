package com.msa.template.order.infra.repository;


import com.msa.template.order.domain.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

	List<OrderItemEntity> findByOrderIdIn(List<Long> orderIds);

	void deleteByOrderId(Long orderId);
}
