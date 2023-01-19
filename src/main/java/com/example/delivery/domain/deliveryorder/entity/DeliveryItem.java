package com.example.delivery.domain.deliveryorder.entity;

import jakarta.persistence.*;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private DeliveryOrder deliveryOrder;
    private String itemName;
    private int count;
    private LocalDateTime creatAt;

    public DeliveryItem(Long id, DeliveryOrder deliveryOrder, String itemName, int count, LocalDateTime creatAt) {
        this.id = id;
        this.deliveryOrder = deliveryOrder;
        this.itemName = itemName;
        this.count = count;
        this.creatAt = creatAt == null ? LocalDateTime.now() : creatAt;
    }

    public static DeliveryItem makeDeliveryItem(DeliveryOrder deliveryOrder, String itemName, int count) {
        return DeliveryItem.builder()
                .deliveryOrder(deliveryOrder)
                .itemName(itemName)
                .count(count)
                .build();
    }
}
