package Vacationproject.shoppingMall.domain.product.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.cart.model.Cart;
import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.favorite.model.Favorite;
import Vacationproject.shoppingMall.domain.review.model.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.*;
import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.UpdateProductRequest;
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
    @Min(value = PRODUCT_PRICE_MIN)
    private int price; // double이었으나, int로 수정

    @NotNull
    @Min(value = PRODUCT_QUANTITY_MIN)
    private int stockQuantity; // 상품 재고수량

    @NotNull
    @Size(min = PRODUCT_CONTENT_MIN_SIZE)
    private String content; //상품 설명

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval=true) //orphanRemoval: 참조를 제거하면 자동으로 데이터베이스에서 삭제
    private List<ProductImage> productImageList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval=true) // 상품이 삭제돠면 리뷰들도 삭제
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval=true)
    private List<Favorite> favoriteList = new ArrayList<>();

    private int favoriteCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "categoty_id")
    private Category category;

    @Builder
    public Product(String name, int price, int stockQuantity, String content, Category category) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.content = content;
        this.category = category;
    }

    /* 연관관계 메서드 */
    public void addProductImage(ProductImage productImage) {
        productImageList.add(productImage);
    }
    public void addProductImages(List<ProductImage> productImages) {
        productImageList.addAll(productImages);
    }
    public void addReview(Review review) {
        this.reviewList.add(review);
    }

    /* Using Method */
    public void update(UpdateProductRequest updateProductRequest, Category category) {
        this.name = updateProductRequest.productName();
        this.price = updateProductRequest.productPrice();
        this.stockQuantity = updateProductRequest.stockQuantity();
        this.content = updateProductRequest.productContent();
        this.category = category;
    }

    public void updateWithImage(UpdateProductRequest updateProductRequest, List<ProductImage> productImages, Category category) {
        this.name = updateProductRequest.productName();
        this.price = updateProductRequest.productPrice();
        this.stockQuantity = updateProductRequest.stockQuantity();
        this.content = updateProductRequest.productContent();
        this.category = category;
        this.productImageList.clear();
        this.productImageList.addAll(productImages);
    }

    public void addFavorite() {
        this.favoriteCount += 1;
    }
    public void decreaseFavorite() {
        this.favoriteCount -= 1;
    }
}
