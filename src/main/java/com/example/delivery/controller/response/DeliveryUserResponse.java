package com.example.delivery.controller.response;

import com.example.delivery.domain.user.entity.DeliveryUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class DeliveryUserResponse {
    String userId;
    String userName;

    public static DeliveryUserResponse entityToUserResponse(DeliveryUser deliveryUser) {
        return DeliveryUserResponse.builder()
                .userId(deliveryUser.getUserCustomId())
                .userName(deliveryUser.getName())
                .build();
    }
}
