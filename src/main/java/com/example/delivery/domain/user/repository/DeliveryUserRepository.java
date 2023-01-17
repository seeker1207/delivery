package com.example.delivery.domain.user.repository;

import com.example.delivery.domain.user.entity.DeliveryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryUserRepository extends JpaRepository<DeliveryUser, Long> {

}
