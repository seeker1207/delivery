package com.example.delivery.domain.deliveryorder.entity;

import com.example.delivery.domain.user.entity.DeliveryUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "delivery_user_id")
    private DeliveryUser deliveryUser;
    @OneToMany
    private List<DeliveryItem> deliveryItemList = new ArrayList<>();

    private LocalDateTime orderDate;
    private LocalDateTime modifyAt;

    public DeliveryOrder(long id, DeliveryUser deliveryUser, List<DeliveryItem> deliveryItemList, LocalDateTime modifyAt, LocalDateTime orderDate) {
        this.id = id;
        this.deliveryUser = deliveryUser;
        this.deliveryItemList.addAll(deliveryItemList);
        this.modifyAt = modifyAt;
        this.orderDate = orderDate;
    }

    static public DeliveryOrder makeDeliveryOrder(DeliveryUser user, List<DeliveryItem> itemIdList) {
        return DeliveryOrder.builder()
                .deliveryUser(user)
                .deliveryItemList(itemIdList)
                .modifyAt(LocalDateTime.now())
                .orderDate(LocalDateTime.now())
                .build();
    }


}
