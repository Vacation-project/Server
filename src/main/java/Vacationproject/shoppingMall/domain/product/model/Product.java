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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;
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
    @Size(min = PRODUCT_PRICE_MIN)
    private int price; // double이었으나, int로 수정

    @NotNull
    @Size(min = PRODUCT_QUANTITY_MIN_SIZE)
    private int stockQuantity; // 상품 재고수량

    @NotNull
    @Size(min = PRODUCT_CONTENT_MIN_SIZE)
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

    /* Using Method */
    public void update(UpdateProductRequest updateProductRequest, List<ProductImage> productImages, Category category) {
        this.name = updateProductRequest.productName();
        this.price = updateProductRequest.productPrice();
        this.stockQuantity = updateProductRequest.stockQuantity();
        this.content = updateProductRequest.productComment();
        this.category = category;
        this.productImageList.clear();
        this.productImageList.addAll(productImages);
    }

    /*
     리뷰 클래스에서 setProduct 메서드에서 발생하는 문제는 Product 클래스에
     getReviews 메서드가 없거나, getReviews 메서드가 List<Review>
     타입의 리뷰 목록을 올바르게 반환하지 않기 때문이라고 판단.
     Product 클래스에 정의된 reviewList 필드에 접근할 수 있는 getter 메서드추가
     즉, Err를 해결하려면,Product 클래스에는 이미 reviewList 필드와 관련된 List<Review> 타입을 반환하는
     getter가 정의되어 있어야 하기 떄문에 추가해준다.
     */
    public List<Review> getReviews() {
        return reviewList;
    }

}
