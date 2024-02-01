package Vacationproject.shoppingMall.domain.favorite.model;

import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

/**
 * 즐겨찾기
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    private int count; // 찜 갯수 노출하기 위함

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // 카테고리와의 관계를 매핑하는 부분이고 기타 메소드
}
