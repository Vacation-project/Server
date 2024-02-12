package Vacationproject.shoppingMall.domain.review.service;

import Vacationproject.shoppingMall.domain.orderProduct.model.OrderProduct;
import Vacationproject.shoppingMall.domain.orderProduct.repository.OrderProductQueryRepository;
import Vacationproject.shoppingMall.domain.orderProduct.repository.OrderProductRepository;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.review.exception.ReviewException;
import Vacationproject.shoppingMall.domain.review.model.Review;
import Vacationproject.shoppingMall.domain.review.repository.ReviewRepository;
import Vacationproject.shoppingMall.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static Vacationproject.shoppingMall.common.Error.exception.ErrorCode.REVIEW_ALREADY_EXISTS;
import static Vacationproject.shoppingMall.domain.orderProduct.dto.OrderProductDto.NotWrittenReviewOrderProduct;
import static Vacationproject.shoppingMall.domain.orderProduct.dto.OrderProductDto.OrderProductReviewResponse;
import static Vacationproject.shoppingMall.domain.review.dto.ReviewDto.ReviewMassage;
import static Vacationproject.shoppingMall.domain.review.dto.ReviewDto.ReviewRequest;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderProductQueryRepository orderProductQueryRepository;
    private final OrderProductRepository orderProductRepository;

    /* 리뷰가 작성되지 않은 주문 상품 정보 가져오기 */
    public List<NotWrittenReviewOrderProduct> notWrittenReviews(Long userId, int offset, int limit) {
        List<OrderProduct> orderProducts = orderProductQueryRepository.findNotWrittenReviewOrderProducts(userId, offset, limit);

        return orderProducts
                .stream().map(it -> NotWrittenReviewOrderProduct.of(it, it.getProduct())).toList();
    }

    public OrderProductReviewResponse getOrderProductName(Long orderProductId) {
        OrderProduct orderProduct = orderProductQueryRepository.findOrderProductAndProduct(orderProductId);
        return OrderProductReviewResponse.of(orderProduct);

    }

    public ReviewMassage writeReview(Long orderProductId, ReviewRequest reviewRequest, User user) {
        OrderProduct orderProduct = orderProductQueryRepository.findOrderProductAndProduct(orderProductId);
        reviewExistsCheck(orderProduct);

        Product product = orderProduct.getProduct();

        Review review = reviewRequest.toEntity(user, product);
        reviewRepository.save(review);

        user.addReview(review);
        product.addReview(review);
        orderProduct.reviewCheck();

        return new ReviewMassage(true);
    }

    private static void reviewExistsCheck(OrderProduct orderProduct) {
        if (!orderProduct.getReviewCheck()) {
            throw new ReviewException(REVIEW_ALREADY_EXISTS);
        }
    }
}
