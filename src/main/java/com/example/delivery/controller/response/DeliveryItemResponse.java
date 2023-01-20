package com.example.delivery.controller.response;

import com.example.delivery.domain.deliveryorder.entity.DeliveryItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class DeliveryItemResponse {
    private String itemName;
    private int count;

    public static DeliveryItemResponse entityToResponse(DeliveryItem deliveryItem) {
        return DeliveryItemResponse.builder()
                .itemName(deliveryItem.getItemName())
                .count(deliveryItem.getCount())
                .build();
    }

    public static List<DeliveryItemResponse> entityToResponse(List<DeliveryItem> deliveryItemList) {
        List<DeliveryItemResponse> result = new ArrayList<>();
        for (DeliveryItem deliveryItem : deliveryItemList) {
            result.add(DeliveryItemResponse.entityToResponse(deliveryItem));
        }
        return result;
    }
}
