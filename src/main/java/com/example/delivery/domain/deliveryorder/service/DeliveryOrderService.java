package com.example.delivery.domain.deliveryorder.service;

import com.example.delivery.domain.deliveryorder.dto.DeliveryOrderDto;
import com.example.delivery.domain.deliveryorder.dto.DeliveryOrderItemDto;
import com.example.delivery.domain.deliveryorder.entity.DeliveryItem;
import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import com.example.delivery.domain.deliveryorder.exception.TooLongDaysFromStartDateToEndDateException;
import com.example.delivery.domain.deliveryorder.repository.DeliveryItemRepository;
import com.example.delivery.domain.deliveryorder.repository.DeliveryOrderRepository;
import com.example.delivery.domain.user.entity.DeliveryUser;
import com.example.delivery.domain.user.repository.DeliveryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryOrderService {
    private final DeliveryItemRepository deliveryItemRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryUserRepository deliveryUserRepository;

    public DeliveryOrder createDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        String userId = deliveryOrderDto.getDeliveryUserId();
        List<DeliveryOrderItemDto> deliveryOrderItemDtoList = deliveryOrderDto.getDeliveryItemList();
        String toAddress = deliveryOrderDto.getToAddress();
        String remark = deliveryOrderDto.getRemark();

        // 새로운 배달정보 생성 및 저장
        DeliveryUser user = deliveryUserRepository.findByUserId(userId).orElseThrow();

        DeliveryOrder newDeliveryOrder = DeliveryOrder.makeDeliveryOrder(user, toAddress, remark);

        deliveryOrderRepository.save(newDeliveryOrder);

        // 새로운 배달 아이템 생성 및 저장
        List<DeliveryItem> newDeliveryItemList = new ArrayList<>();
        for (DeliveryOrderItemDto deliveryItem : deliveryOrderItemDtoList) {
            newDeliveryItemList.add(DeliveryItem.makeDeliveryItem(
                    newDeliveryOrder, deliveryItem.getOrderItemName(), deliveryItem.getCount()));
        }

        List<DeliveryItem> deliveryItemList = deliveryItemRepository.saveAll(newDeliveryItemList);

        // 배달 주문 엔티티에 배달 아이템 저장
        newDeliveryOrder.addDeliveryItem(deliveryItemList);

        return newDeliveryOrder;
    }

    public List<DeliveryOrder> getDeliveryOrderByDate(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        if (ChronoUnit.DAYS.between(startDate, endDate) > 3L) {
            throw new TooLongDaysFromStartDateToEndDateException();
        }

        return deliveryOrderRepository.findAllByDeliveryUserIdAndOrderDateBetween(userId, startDate, endDate);
    }

    public DeliveryOrder updateDeliveryDateInDeliveryOrder(Long deliveryOrderId, String toAddress) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(deliveryOrderId).orElseThrow();

        deliveryOrder.updateToAddress(toAddress);
        deliveryOrderRepository.save(deliveryOrder);

        return deliveryOrder;
    }
}
