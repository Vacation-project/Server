package Vacationproject.shoppingMall.domain.review.model;

import Vacationproject.shoppingMall.common.model.BaseEntity;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 상품 후기
 */
@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String comment;

    @Column(nullable = false)
    private int rating;

    // 이미지 저장을 위한 필드 (선택적 구현)
    private String imagePath;
    private String imageName;

    // 후기 작성 날짜는 BaseEntity에 이미 createdAt으로 관리되므로 별도로 추가하지 않음

    // 리뷰와 관련된 상품을 설정하는 메서드
    public void setProduct(Product product) {
        this.product = product;
        // 중복 추가 방지
        if (product != null && !product.getReviews().contains(this)) {
            product.getReviews().add(this);
        }
    }

    // 리뷰 작성자를 설정하는 메서드
    public void setUser(User user) {
        this.user = user;
        user.getReviewList().add(this);
    }

    // 리뷰 정보를 업데이트하는 메서드
    public void updateReview(String title, String comment, int rating) {
        this.title = title;
        this.comment = comment;
        this.rating = rating;
    }

    // 이미지 정보 업데이트 (선택적 구현)
    public void updateImageInfo(String imagePath, String imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    // Review 클래스 내에서 생성 날짜를 가져옴 (베이스 엔티티 수정)
    public LocalDateTime getCreatedDate() {
        return getCreatedAt();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
