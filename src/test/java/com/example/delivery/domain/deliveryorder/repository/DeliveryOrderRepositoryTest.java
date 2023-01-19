package com.example.delivery.domain.deliveryorder.repository;

import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import com.example.delivery.domain.user.entity.DeliveryUser;
import com.example.delivery.domain.user.repository.DeliveryUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryOrderRepositoryTest {
    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
    @Autowired
    private DeliveryUserRepository deliveryUserRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    @DisplayName("회원 아이디와 주문 날짜로 배달 주문정보를 조회한다.")
    void getDeliveryOrderByUserIdAndOrderDate() {
        //given
        DeliveryUser deliveryUser =
                DeliveryUser.builder()
                            .userId("seeker1207")
                            .password("1234!@#$aAabcdefsdf")
                            .username("테스트")
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
//        testEntityManager.getEntityManager().getTransaction().commit();
        //then
        System.out.println(deliveryOrderRepository.findAll().size());
        System.out.println(deliveryUserRepository.findAll().size());
        List<DeliveryOrder> deliveryOrderList = deliveryOrderRepository.findAllByDeliveryUserIdAndOrderDateBetween(
                "seeker1207"
        );

        System.out.println(deliveryOrderList);

        Assertions.assertEquals(1, deliveryOrderList.size());
    }
}