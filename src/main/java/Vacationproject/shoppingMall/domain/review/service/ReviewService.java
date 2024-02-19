package Vacationproject.shoppingMall.domain.review.service;

import Vacationproject.shoppingMall.common.Error.exception.EntityNotFoundException;
import Vacationproject.shoppingMall.domain.product.dto.ProductDto;
import Vacationproject.shoppingMall.domain.review.dto.ReviewDto;
import Vacationproject.shoppingMall.domain.review.dto.ReviewUpdateDto;
import Vacationproject.shoppingMall.domain.review.model.Review;
import Vacationproject.shoppingMall.domain.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//import javax.persistence.EntityNotFoundException;

/**
 * 리뷰 관리 CRUD
 * 관리자가 리뷰를 지워야하니까 일단 구현
 */
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 리뷰 조회
    @Transactional(readOnly = true)
    public Review findReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));
    }

    // 리뷰 생성
    @Transactional
    public Review createReview(Review review) {
        // 리뷰 생성 로직
        return reviewRepository.save(review);
    }

    // 리뷰 수정
    /*@Transactional
    public Review updateReview(Long id, ReviewUpdateDto updatedReview) {
        Review review = findReviewById(id);
        // 리뷰 수정 로직, 예를 들어
        review.setTitle(updatedReview.getTitle());
        review.setComment(updatedReview.getComment());
        review.setRating(updatedReview.getRating());
        return reviewRepository.save(review);
    }*/
    @Transactional
    public Review updateReview(Long id, ReviewUpdateDto updatedReview) {
        Review review = findReviewById(id);
        // 리뷰 정보 수정
        review.setTitle(updatedReview.getTitle());
        review.setComment(updatedReview.getComment());
        review.setRating(updatedReview.getRating());
        // 상품 정보는 수정하지 않으므로, 여기에서 setProduct 호출을 하지 않음
        return review; // JPA의 변경 감지(dirty checking) 기능으로 인해 save 호출이 필요 없음
    }

    // 관리자에 의한 리뷰 삭제
    @Transactional
    public void deleteReviewByAdmin(Long reviewId) {
        Review review = findReviewById(reviewId);
        reviewRepository.delete(review);
    }

    // 02.19 추가 -> 수정 필요.
    public ProductDto getReviewData(Long orderItemId) {
        Review orderItem = findReviewById(orderItemId);
        reviewRepository.equals(findReviewById(orderItemId));
        return null;
    }

    public void createReview(Long orderItemId, ReviewDto reviewDto) {

    }

    public List<ReviewDto> getUserReviews() {
        return null;

    }

    public void deleteReview(Long reviewId) {

    }
}