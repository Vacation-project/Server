package Vacationproject.shoppingMall.domain.etc;

import Vacationproject.shoppingMall.domain.order.model.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 배송 정보
 */
@Getter
//@Entity
@Table(name = "delivery")
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order orderId;

    private String deliveryAddress;
    private String deliveryStatus; // 배송상태
    private LocalDateTime estimatedDeliveryDate; // 배송받는 날짜
}
