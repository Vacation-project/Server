package Vacationproject.shoppingMall.domain.orderProduct.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.order.model.Order;
import Vacationproject.shoppingMall.domain.product.model.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

/**
 * 주문 상품_상세
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;

    private int quantity;

    private int orderPrice;

    private Boolean reviewCheck = false;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id") //FK
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /* 편의 메서드 */
    public void reviewCheck() {
        this.reviewCheck = true;
    }
}
