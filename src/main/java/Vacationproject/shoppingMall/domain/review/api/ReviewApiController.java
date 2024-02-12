package Vacationproject.shoppingMall.domain.review.api;

import Vacationproject.shoppingMall.common.constant.SwaggerConstants;
import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.ORDER_PRODUCT_Id;
import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.TAG_REVIEW_DESCRIPTION;
import static Vacationproject.shoppingMall.common.dto.ApiResponse.success;
import static Vacationproject.shoppingMall.domain.orderProduct.dto.OrderProductDto.NotWrittenReviewOrderProduct;
import static Vacationproject.shoppingMall.domain.orderProduct.dto.OrderProductDto.OrderProductReviewResponse;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@Tag(name = SwaggerConstants.TAG_REVIEW, description = TAG_REVIEW_DESCRIPTION)
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/user")
    public ApiResponse<List<NotWrittenReviewOrderProduct>> notWrittenReviews(
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        List<NotWrittenReviewOrderProduct> notWrittenReviewOrderProducts = reviewService.notWrittenReviews(1L);
        return success(notWrittenReviewOrderProducts);
    }

    @GetMapping("/{orderProductId}")
    public ApiResponse writeReviewForm(@PathVariable(name = ORDER_PRODUCT_Id) Long orderProductId) {
        OrderProductReviewResponse orderProductReviewResponse = reviewService.getOrderProductName(orderProductId);
        return success(orderProductReviewResponse);
    }


}
