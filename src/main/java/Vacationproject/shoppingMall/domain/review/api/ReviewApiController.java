package Vacationproject.shoppingMall.domain.review.api;

import Vacationproject.shoppingMall.common.constant.SwaggerConstants;
import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.review.dto.ReviewDto;
import Vacationproject.shoppingMall.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.ORDER_PRODUCT_Id;
import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.TAG_REVIEW_DESCRIPTION;
import static Vacationproject.shoppingMall.common.dto.ApiResponse.success;
import static Vacationproject.shoppingMall.domain.orderProduct.dto.OrderProductDto.NotWrittenReviewOrderProduct;
import static Vacationproject.shoppingMall.domain.orderProduct.dto.OrderProductDto.OrderProductReviewResponse;
import static Vacationproject.shoppingMall.domain.review.dto.ReviewDto.*;
import static Vacationproject.shoppingMall.domain.review.dto.ReviewDto.UserReviewResponse;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = SwaggerConstants.TAG_REVIEW, description = TAG_REVIEW_DESCRIPTION)
public class ReviewApiController {

    private final ReviewService reviewService;


    @GetMapping("/user")
    public ApiResponse<List<NotWrittenReviewOrderProduct>> notWrittenReviews(
//            @AuthenticationPrincipal PrincipalDetails principalDetails
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "9") int limit
    ) {
        /* 회원 검증 추가 */

        List<NotWrittenReviewOrderProduct> notWrittenReviewOrderProducts = reviewService.notWrittenReviews(1L, offset, limit);
        return success(notWrittenReviewOrderProducts);
    }

    @GetMapping("/{orderProductId}")
    public ApiResponse<OrderProductReviewResponse> writeReviewForm(@PathVariable(name = ORDER_PRODUCT_Id) Long orderProductId) {
        /* 회원 검증 추가 */

        OrderProductReviewResponse orderProductReviewResponse = reviewService.getOrderProductName(orderProductId);
        return success(orderProductReviewResponse);
    }

    @PostMapping("/{orderProductId}")
    public ApiResponse<ReviewMassage> writeReview(@PathVariable(name = ORDER_PRODUCT_Id) Long orderProductId,
                                                  @RequestBody @Valid WriteReviewRequest writeReviewRequest) {
        /* 회원 검증 추가 */

        ReviewMassage reviewMassage = reviewService.writeReview(orderProductId, writeReviewRequest, User);

        return success(reviewMassage);
    }

    @GetMapping("/user/written")
    public ApiResponse<List<UserReviewResponse>> writtenReview(
            //            @AuthenticationPrincipal PrincipalDetails principalDetails
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "9") int limit
    ) {
        List<UserReviewResponse> userReviewResponses = reviewService.getWrittenReview(1L, offset, limit);

        return success(userReviewResponses);
    }

    @GetMapping("/")
}
