package com.example.delivery.domain.deliveryorder.entity;

import com.example.delivery.domain.user.entity.DeliveryUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@ToString
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_order_id")
    private long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private DeliveryUser deliveryUser;
    @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL)
    private List<DeliveryItem> deliveryItemList = new ArrayList<>();

    private String toAddress;
    private String remark;
    private LocalDateTime orderDate;
    private LocalDateTime modifyAt;

    public DeliveryOrder(long id, DeliveryUser deliveryUser, List<DeliveryItem> deliveryItemList,
                         String toAddress,  String remark, LocalDateTime orderDate,  LocalDateTime modifyAt) {
        this.id = id;
        this.deliveryUser = deliveryUser;
        if (deliveryItemList != null && deliveryItemList.size() > 0) {
            this.deliveryItemList.addAll(deliveryItemList);
        }
        this.toAddress = toAddress;
        this.remark = remark;
        this.orderDate = orderDate;
        this.modifyAt = modifyAt;
    }

    static public DeliveryOrder makeDeliveryOrder(DeliveryUser user, String toAddress, String remark) {
        return DeliveryOrder.builder()
                .deliveryUser(user)
                .toAddress(toAddress)
                .orderDate(LocalDateTime.now())
                .remark(remark)
                .modifyAt(LocalDateTime.now())
                .build();
    }


}
