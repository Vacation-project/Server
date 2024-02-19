package Vacationproject.shoppingMall.domain.user.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.cart.model.Cart;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.review.model.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Admin extends BaseEntity {

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

    // 사용자가 작성한 리뷰
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 관리자가 작성한 리뷰
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "admin_id") // 관리자와의 관계를 위한 JoinColumn 추가
    private Admin admin;

    // 관리자 설정 메서드
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    // 사용자 설정 메서드
    public void setUser(User user) {
        this.user = user;
    }

}
