package com.example.delivery.controller.response;

import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class DeliveryOrderResponse {
    private String userId;
    private List<DeliveryItemResponse> deliveryItemResponseList;
    private String toAddress;
    private String remark;
    private LocalDateTime orderDate;


    public static DeliveryOrderResponse entityToOrderResponse(DeliveryOrder deliveryOrder) {
        return DeliveryOrderResponse.builder()
                .userId(deliveryOrder.getDeliveryUser().getUserId())
                .deliveryItemResponseList(DeliveryItemResponse.entityToResponse(deliveryOrder.getDeliveryItemList()))
                .toAddress(deliveryOrder.getToAddress())
                .remark(deliveryOrder.getRemark())
                .orderDate(deliveryOrder.getOrderDate())
                .build();
    }

    public static List<DeliveryOrderResponse> entityToOrderResponse(List<DeliveryOrder> deliveryOrderList) {
        List<DeliveryOrderResponse> result = new ArrayList<>();
        for (DeliveryOrder deliveryOrder : deliveryOrderList) {
            result.add(entityToOrderResponse(deliveryOrder));
        }

        return result;
    }
}
