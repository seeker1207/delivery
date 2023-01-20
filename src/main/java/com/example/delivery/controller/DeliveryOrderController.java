package com.example.delivery.controller;

import com.example.delivery.controller.response.DeliveryOrderResponse;
import com.example.delivery.domain.deliveryorder.dto.DeliveryOrderDto;
import com.example.delivery.domain.deliveryorder.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DeliveryOrderController {
    private final DeliveryOrderService deliveryOrderService;

    @PostMapping("/delivery")
    public void createDeliveryOrder(@RequestBody DeliveryOrderDto deliveryOrderDto) {
        deliveryOrderService.createDeliveryOrder(deliveryOrderDto);
    }

    @GetMapping("/delivery")
    public List<DeliveryOrderResponse> getDeliveryOrderByDate(
            @RequestParam String userId,
            @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {

        return DeliveryOrderResponse.entityToOrderResponse(
                deliveryOrderService.getDeliveryOrderByDate(userId, startDate, endDate)
        );
    }


}
