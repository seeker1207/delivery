package com.example.delivery.domain.deliveryorder.repository;

import com.example.delivery.domain.deliveryorder.entity.DeliveryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long> {

}
