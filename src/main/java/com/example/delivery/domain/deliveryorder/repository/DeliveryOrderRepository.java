package com.example.delivery.domain.deliveryorder.repository;

import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
}
