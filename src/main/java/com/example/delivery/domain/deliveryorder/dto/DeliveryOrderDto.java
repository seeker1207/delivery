package com.example.delivery.domain.deliveryorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryOrderDto {
    private List<DeliveryOrderItemDto> deliveryItemList;
    private String deliveryUserId;
    private String toAddress;
    private String remark;


}
