package com.example.delivery.domain.deliveryorder.service;

import com.example.delivery.domain.deliveryorder.dto.DeliveryOrderDto;
import com.example.delivery.domain.deliveryorder.dto.DeliveryOrderItemDto;
import com.example.delivery.domain.deliveryorder.entity.DeliveryItem;
import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import com.example.delivery.domain.deliveryorder.repository.DeliveryItemRepository;
import com.example.delivery.domain.deliveryorder.repository.DeliveryOrderRepository;
import com.example.delivery.domain.user.entity.DeliveryUser;
import com.example.delivery.domain.user.repository.DeliveryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryOrderService {
    private final DeliveryItemRepository deliveryItemRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryUserRepository deliveryUserRepository;

    public void createDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        Long userId = deliveryOrderDto.getDeliveryUserId();
        List<DeliveryOrderItemDto> deliveryOrderItemDtoList = deliveryOrderDto.getDeliveryItemList();
        String toAddress = deliveryOrderDto.getToAddress();
        String remark = deliveryOrderDto.getRemark();

        DeliveryUser user = deliveryUserRepository.findById(userId).orElseThrow();

        DeliveryOrder newDeliveryOrder = DeliveryOrder.makeDeliveryOrder(user, toAddress, remark);

        deliveryOrderRepository.save(newDeliveryOrder);

        List<DeliveryItem> newDeliveryItemList = new ArrayList<>();
        for (DeliveryOrderItemDto deliveryItem : deliveryOrderItemDtoList) {
            newDeliveryItemList.add(DeliveryItem.makeDeliveryItem(
                    newDeliveryOrder, deliveryItem.getOrderItemName(), deliveryItem.getCount()));
        }

        deliveryItemRepository.saveAll(newDeliveryItemList);
    }

    public DeliveryOrder getDeliveryOrderByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
