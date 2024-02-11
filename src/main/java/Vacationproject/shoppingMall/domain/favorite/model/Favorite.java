package Vacationproject.shoppingMall.domain.favorite.model;

import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /*연관관계 메서드*/
    @Builder
    public Favorite(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
