package Vacationproject.shoppingMall.domain.order.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.orderProduct.model.OrderProduct;
import Vacationproject.shoppingMall.domain.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders") //order는 예약어로 지정되어 있을수가 있기 때문에 관례로 orders로 해준다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProductList = new ArrayList<>();

    // 카테고리와의 관계를 매핑하는 부분이고 기타 메소드
}
