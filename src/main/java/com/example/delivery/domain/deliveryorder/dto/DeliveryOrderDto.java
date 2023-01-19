package com.example.delivery.domain.deliveryorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderDto {
    private List<DeliveryOrderItemDto> deliveryItemList;
    private Long deliveryUserId;
    private String toAddress;
    private String remark;


}
