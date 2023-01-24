package com.example.delivery.domain.deliveryorder.repository;

import com.example.delivery.domain.deliveryorder.entity.DeliveryItem;
import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import com.example.delivery.domain.user.entity.DeliveryUser;
import com.example.delivery.domain.user.repository.DeliveryUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryOrderRepositoryTest {
    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
    @Autowired
    private DeliveryUserRepository deliveryUserRepository;
    @Autowired
    private DeliveryItemRepository deliveryItemRepository;

    @Test
    @DisplayName("회원 아이디와 주문 날짜로 배달 주문정보를 조회한다.")
    void getDeliveryOrderByUserIdAndOrderDate() {
        //given
        DeliveryUser deliveryUser =
                DeliveryUser.builder()
                        .userCustomId("seeker1207")
                        .password("1234!@#$aAabcdefsdf")
                        .name("테스트")
                        .build();
        deliveryUserRepository.save(deliveryUser);

        //when
        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .deliveryUser(deliveryUser)
                .orderDate(LocalDateTime.of(2023, 1, 19, 0, 0))
                .remark("주문 특이사항")
                .toAddress("서울특별시 행복구 행복동")
                .build();
        deliveryOrderRepository.save(deliveryOrder);

        DeliveryItem deliveryItem = DeliveryItem.builder()
                .itemName("교촌 허니콤보 치킨")
                .deliveryOrder(deliveryOrder)
                .count(2)
                .build();
        deliveryItemRepository.save(deliveryItem);

        deliveryOrder.getDeliveryItemList().add(deliveryItem);

        //then
        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository
                .findAllByDeliveryUserIdAndOrderDateBetween("seeker1207",
                        LocalDateTime.of(2023, 1, 18, 0, 0),
                        LocalDateTime.of(2023, 1, 19, 0, 1));

        DeliveryOrder result = deliveryOrderList.get(0);
        Assertions.assertEquals("seeker1207", result.getDeliveryUser().getUserCustomId());
        Assertions.assertEquals("교촌 허니콤보 치킨", result.getDeliveryItemList().get(0).getItemName());
        Assertions.assertEquals("서울특별시 행복구 행복동", result.getToAddress());
    }
}