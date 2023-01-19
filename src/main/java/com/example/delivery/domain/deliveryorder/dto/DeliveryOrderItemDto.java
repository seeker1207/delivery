package com.example.delivery.domain.deliveryorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class DeliveryOrderItemDto {
    private String orderItemName;
    private int count;
}
