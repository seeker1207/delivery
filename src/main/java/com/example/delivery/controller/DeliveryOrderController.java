package com.example.delivery.controller;

import com.example.delivery.domain.deliveryorder.dto.DeliveryOrderDto;
import com.example.delivery.domain.deliveryorder.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeliveryOrderController {
    private final DeliveryOrderService deliveryOrderService;

    @PostMapping("/delivery")
    public void createDeliveryOrder(@RequestBody DeliveryOrderDto deliveryOrderDto) {

        deliveryOrderService.createDeliveryOrder(deliveryOrderDto);
    }


}
