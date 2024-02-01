package Vacationproject.shoppingMall.domain.review.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

/**
 * 상품후기
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String title; // 제목

    private String comment; // 후기 내용

    private int rating; // 평점

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
