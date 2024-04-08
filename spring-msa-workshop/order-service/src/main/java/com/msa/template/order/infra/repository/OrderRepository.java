package com.msa.template.order.infra.repository;


import com.msa.template.order.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
	extends JpaRepository<OrderEntity, Long>, OrderRepositoryCustom {
}
