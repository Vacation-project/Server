package Vacationproject.shoppingMall.domain.review.controller;

import Vacationproject.shoppingMall.domain.order.dto.OrderItemDto;
import Vacationproject.shoppingMall.domain.order.service.OrderItemService;
import Vacationproject.shoppingMall.domain.product.dto.ProductDto;
import Vacationproject.shoppingMall.domain.review.dto.ReviewDto;
import Vacationproject.shoppingMall.domain.review.dto.ReviewUpdateDto;
import Vacationproject.shoppingMall.domain.review.repository.ReviewRepository;
import Vacationproject.shoppingMall.domain.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
관리자만 리뷰를 삭제할 수 있도록, 컨트롤러에서는 해당 요청을 처리하기 전에
 사용자의 권한을 확인해야 합니다. Spring Security를 사용하고 있다면,
  @PreAuthorize 어노테이션을 활용하여 관리자 권한을 가진 사용자만
  해당 메서드에 접근할 수 있도록 제한,

 */
@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderItemService orderItemService; // 주문 항목 서비스 추가

    // 생성자 주입을 통한 서비스 연결
    public ReviewController(ReviewService reviewService, OrderItemService orderItemService) {
        this.reviewService = reviewService;
        this.orderItemService = orderItemService;
    }

    /*
    해당 메서드에서 Could not autowire. No beans of 'OrderItemService' type found. 발생
    ->  에러 메시지는 Spring이 OrderItemService 타입의 빈을 찾을 수 없다는 것을 의미한다.
    OrderItemService 인터페이스나 구현 클래스에 @Service 어노테이션을 추가하여 Spring이 이를 빈으로 등록할 수 있도록 어노테이션 추가함.
     */

    // 마이 페이지 - 리뷰 작성 가능한 주문 항목 조회
    @GetMapping("/user/review")
    public ResponseEntity<List<OrderItemDto>> getReviewableOrderItems() {
        List<OrderItemDto> orderItems = orderItemService.findReviewableOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    // 후기 작성 페이지 데이터 로딩
    @GetMapping("/review/{orderItemId}")
    public ResponseEntity<ProductDto> getReviewData(@PathVariable Long orderItemId) {
        ProductDto product = reviewService.getReviewData(orderItemId);
        return ResponseEntity.ok(product);
    }

    // 후기 생성
    @PostMapping("/products/{orderItemId}/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long orderItemId, @RequestBody ReviewDto reviewDto) {
        reviewService.createReview(orderItemId, reviewDto);
        return ResponseEntity.ok("Review created successfully");
    }

    // 마이 페이지 - 후기 조회
    @GetMapping("/user/reviews")
    public ResponseEntity<List<ReviewDto>> getUserReviews() {
        List<ReviewDto> reviews = reviewService.getUserReviews();
        return ResponseEntity.ok(reviews);
    }

    // 후기 수정
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody ReviewUpdateDto reviewUpdateDto) {
        reviewService.updateReview(reviewId, reviewUpdateDto);
        return ResponseEntity.ok("Review updated successfully");
    }

    // 후기 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }

    // 관리자에 의한 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    @PreAuthorize("hasRole('ADMIN')") // ADMIN 권한을 가진 사용자만 접근 가능
    public ResponseEntity<?> deleteReviewByAdmin(@PathVariable Long reviewId) {
        reviewService.deleteReviewByAdmin(reviewId);
        return ResponseEntity.ok().build();
    }
}
