package com.example.delivery.domain.deliveryorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DeliveryOrderToAddressOnlyDto {
    private String toAddress;
}
