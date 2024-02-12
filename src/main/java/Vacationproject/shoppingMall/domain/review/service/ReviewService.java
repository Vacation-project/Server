package Vacationproject.shoppingMall.domain.review.service;

import Vacationproject.shoppingMall.domain.orderProduct.model.OrderProduct;
import Vacationproject.shoppingMall.domain.orderProduct.repository.OrderProductQueryRepository;
import Vacationproject.shoppingMall.domain.review.repository.ReviewRepository;
import Vacationproject.shoppingMall.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static Vacationproject.shoppingMall.domain.orderProduct.dto.OrderProductDto.NotWrittenReviewOrderProduct;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderProductQueryRepository orderProductQueryRepository;

    /* 리뷰가 작성되지 않은 주문 상품 정보 가져오기 */
    public List<NotWrittenReviewOrderProduct> notWrittenReviews(Long userId) {
        List<OrderProduct> orderProducts = orderProductQueryRepository.findNotWrittenReviewOrderProducts(userId);

        return orderProducts
                .stream().map(it -> NotWrittenReviewOrderProduct.of(it, it.getProduct())).toList()
    }
}
