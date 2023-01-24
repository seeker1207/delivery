package com.example.delivery.domain.user.repository;

import com.example.delivery.domain.user.entity.DeliveryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryUserRepository extends JpaRepository<DeliveryUser, Long> {
    Optional<DeliveryUser> findByUserCustomId(String userId);
}
