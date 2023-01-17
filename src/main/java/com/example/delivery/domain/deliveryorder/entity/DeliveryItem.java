package com.example.delivery.domain.deliveryorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DeliveryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private int count;
    private LocalDateTime creatAt;

    public DeliveryItem(Long id, String itemName, int count, LocalDateTime creatAt) {
        this.id = id;
        this.itemName = itemName;
        this.count = count;
        this.creatAt = creatAt;
    }
}
