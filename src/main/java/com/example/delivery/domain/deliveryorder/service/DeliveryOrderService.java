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

        DeliveryUser user = deliveryUserRepository.findByUserId(userId).orElseThrow();

        DeliveryOrder newDeliveryOrder = DeliveryOrder.makeDeliveryOrder(user, toAddress, remark);

        deliveryOrderRepository.save(newDeliveryOrder);

        List<DeliveryItem> newDeliveryItemList = new ArrayList<>();
        for (DeliveryOrderItemDto deliveryItem : deliveryOrderItemDtoList) {
            newDeliveryItemList.add(DeliveryItem.makeDeliveryItem(
                    newDeliveryOrder, deliveryItem.getOrderItemName(), deliveryItem.getCount()));
        }

        List<DeliveryItem> deliveryItemList = deliveryItemRepository.saveAll(newDeliveryItemList);

        newDeliveryOrder.addDeliveryItem(deliveryItemList);

        return newDeliveryOrder;
    }

    public List<DeliveryOrder> getDeliveryOrderByDate(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        if (ChronoUnit.DAYS.between(startDate, endDate) > 3L) {
            throw new TooLongDaysFromStartDateToEndDateException();
        }

        return deliveryOrderRepository.findAllByDeliveryUserIdAndOrderDateBetween(userId, startDate, endDate);
    }
}
