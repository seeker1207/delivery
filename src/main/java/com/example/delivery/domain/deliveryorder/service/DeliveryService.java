package com.example.delivery.domain.deliveryorder.service;

import com.example.delivery.domain.deliveryorder.dto.DeliveryOrderDto;
import com.example.delivery.domain.deliveryorder.entity.DeliveryItem;
import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import com.example.delivery.domain.deliveryorder.repository.DeliveryItemRepository;
import com.example.delivery.domain.deliveryorder.repository.DeliveryOrderRepository;
import com.example.delivery.domain.user.entity.DeliveryUser;
import com.example.delivery.domain.user.repository.DeliveryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final DeliveryItemRepository deliveryItemRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryUserRepository deliveryUserRepository;

    public void createDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        Long userId = deliveryOrderDto.getDeliveryUserId();
        List<Long> itemIdList = deliveryOrderDto.getDeliveryItemIdList();

        DeliveryUser user = deliveryUserRepository.findById(userId).orElseThrow();
        List<DeliveryItem> deliveryItemList = deliveryItemRepository.findAllById(itemIdList);

        DeliveryOrder newDeliveryOrder = DeliveryOrder.makeDeliveryOrder(user, deliveryItemList);

        deliveryOrderRepository.save(newDeliveryOrder);
    }
}
