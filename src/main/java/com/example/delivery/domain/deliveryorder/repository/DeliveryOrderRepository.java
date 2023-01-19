package com.example.delivery.domain.deliveryorder.repository;

import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
//    @Query("select do " +
//            "from DeliveryOrder do " +
//            "join fetch do.deliveryItemList " +
//            "where do.deliveryUser.userId = :userId and do.orderDate >= :start and do.orderDate <= :end")
//    public List<DeliveryOrder> findAllByDeliveryUserIdAndOrderDateBetween(
//            @Param("userId") String userId, @Param("start")LocalDateTime start, @Param("end") LocalDateTime end);
    @Query("select do " +
            "from DeliveryOrder do " +
            "join fetch do.deliveryItemList " +
            "where do.deliveryUser.userId = :userId")
    public List<DeliveryOrder> findAllByDeliveryUserIdAndOrderDateBetween(
            @Param("userId") String userId);
}
