package Vacationproject.shoppingMall.domain.orderProduct.model;

import Vacationproject.shoppingMall.domain.order.model.Order;
import Vacationproject.shoppingMall.domain.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

/**
 * 주문 상품_상세
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;

    private int quantity;

    private int orderPrice;

    private Boolean reviewCheck;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id") //FK
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore // 왜 추가했는지 질문
    private Product product;
}
