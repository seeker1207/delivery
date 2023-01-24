package com.example.delivery.controller;

import com.example.delivery.controller.response.DeliveryUserResponse;
import com.example.delivery.domain.user.dto.DeliveryUserDto;
import com.example.delivery.domain.user.service.DeliveryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeliveryUserController {
    private final DeliveryUserService deliveryUserService;

    @PostMapping("/user")
    public DeliveryUserResponse signup(@RequestBody DeliveryUserDto deliveryUserDto) {
        return DeliveryUserResponse.entityToUserResponse(deliveryUserService.signUp(deliveryUserDto));
    }
}
