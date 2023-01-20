package com.example.delivery.controller;

import com.example.delivery.controller.response.DeliveryOrderResponse;
import com.example.delivery.domain.deliveryorder.dto.DeliveryOrderDto;
import com.example.delivery.domain.deliveryorder.service.DeliveryOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DeliveryOrderController {
    private final DeliveryOrderService deliveryOrderService;

    @Operation(summary = "배달 주문 저장", description = "유저가 주문한 배달을 저장합니다.")
    @PostMapping("/delivery")
    public void createDeliveryOrder(@RequestBody DeliveryOrderDto deliveryOrderDto) {
        deliveryOrderService.createDeliveryOrder(deliveryOrderDto);
    }

    @Operation(summary = "배달 주문 조회", description = "유저가 지정된 기간안에 주문한 배달을 조회합니다.")
    @GetMapping("/delivery")
    public List<DeliveryOrderResponse> getDeliveryOrderByDate(
            @Parameter(name = "userId", description = "배달을 주문한 유저의 아이디") @RequestParam String userId,
            @Parameter(name = "startDate", description = "시작 날짜 (yyyy-MM-dd'T'HH:mm:ss  ISO 형식으로 입력)")
            @RequestParam LocalDateTime startDate,
            @Parameter(name = "endDate", description = "끝 날짜 (yyyy-MM-dd'T'HH:mm:ss  ISO 형식으로 입력)")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return DeliveryOrderResponse.entityToOrderResponse(
                deliveryOrderService.getDeliveryOrderByDate(userId, startDate, endDate)
        );
    }


}
