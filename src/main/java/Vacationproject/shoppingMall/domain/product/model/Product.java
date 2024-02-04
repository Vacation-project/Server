package Vacationproject.shoppingMall.domain.product.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.cart.model.Cart;
import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.favorite.model.Favorite;
import Vacationproject.shoppingMall.domain.review.model.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.PRODUCT_PRICE_MIN;
import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.PRODUCT_QUANTITY_MIN_SIZE;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(min = PRODUCT_PRICE_MIN)
    private int price; // double이었으나, int로 수정

    @NotNull
    @Size(min = PRODUCT_QUANTITY_MIN_SIZE)
    private int stockQuantity; // 상품 재고수량

    private String content; //상품 설명

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImageList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE) // 상품이 삭제돠면 리뷰들도 삭제
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Favorite> favoriteList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "categoty_id")
    private Category category;

    /* 연관관계 메서드 */
    public void addProductImage(ProductImage productImage) {
        productImageList.add(productImage);
    }
}
