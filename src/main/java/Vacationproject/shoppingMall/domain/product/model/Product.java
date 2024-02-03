package Vacationproject.shoppingMall.domain.product.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.cart.model.Cart;
import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.favorite.model.Favorite;
import Vacationproject.shoppingMall.domain.review.model.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int price; // double이었으나, int로 수정

    private int stockQuantity; // 상품 재고수량

    private int content; //상품 설명

    private String imageUrl; // 상품 이미지 url

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE) // 상품이 삭제돠면 리뷰들도 삭제
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Favorite> favoriteList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImageList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "categoty_id")
    private Category category;
}
