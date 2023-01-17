package com.example.delivery.domain.deliveryorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DeliveryOrderDto {
    private List<Long> deliveryItemIdList;
    private Long deliveryUserId;
    private String fromAddress;
    private String toAddress;


}
