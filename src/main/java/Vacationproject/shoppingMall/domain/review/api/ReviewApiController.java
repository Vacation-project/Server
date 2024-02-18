package Vacationproject.shoppingMall.domain.review.api;

import Vacationproject.shoppingMall.common.constant.SwaggerConstants;
import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.*;
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

    @GetMapping("/{orderProductId}")
    @Operation(summary = WRITE_REVIEW_FORM_SUMMARY, description = WRITE_REVIEW_FORM_DESCRIPTION)
    public ApiResponse<OrderProductReviewResponse> writeReviewForm(@PathVariable(name = ORDER_PRODUCT_Id) final Long orderProductId) {
        /* 회원 검증 추가 */

        OrderProductReviewResponse orderProductReviewResponse = reviewService.getOrderProductName(orderProductId);
        return success(orderProductReviewResponse);
    }

//    @PostMapping("/{orderProductId}")
//    @Operation(summary = WRITE_REVIEW_SUMMARY, description = WRITE_REVIEW_DESCRIPTION)
//    public ApiResponse<ReviewMassage> writeReview(@PathVariable(name = ORDER_PRODUCT_Id) Long orderProductId,
//                                                  @RequestBody @Valid WriteReviewRequest writeReviewRequest) {
//        /* 회원 검증 추가 */
//
//        ReviewMassage reviewMassage = reviewService.writeReview(orderProductId, writeReviewRequest, User);
//
//        return success(reviewMassage);
//    }

    @GetMapping("/user")
    @Operation(summary = NOT_WRITTEN_REVIEW_SUMMARY, description = NOT_WRITTEN_REVIEW_DESCRIPTION)
    public ApiResponse<List<NotWrittenReviewOrderProduct>> notWrittenReviews(
//            @AuthenticationPrincipal PrincipalDetails principalDetails
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "9") int limit
    ) {
        /* 회원 검증 추가 */

        List<NotWrittenReviewOrderProduct> notWrittenReviewOrderProducts = reviewService.getNotWrittenReviews(1L, offset, limit); // 1L은 유저의 ID
        return success(notWrittenReviewOrderProducts);
    }

    @GetMapping("/user/written")
    @Operation(summary = WRITTEN_REVIEW_SUMMARY, description = WRITTEN_REVIEW_DESCRIPTION)
    public ApiResponse<List<UserReviewResponse>> writtenReview(
            //            @AuthenticationPrincipal PrincipalDetails principalDetails
            @RequestParam(name = "offset", defaultValue = "0") final int offset,
            @RequestParam(name = "limit", defaultValue = "9") final int limit
    ) {
        /* 회원 검증 */

        List<UserReviewResponse> userReviewResponses = reviewService.getWrittenReview(1L, offset, limit);

        return success(userReviewResponses);
    }

    @GetMapping("/update/{reviewId}")
    @Operation(summary = UPDATE_REVIEW_FROM_SUMMARY, description = UPDATE_REVIEW_FROM_DESCRIPTION)
    public ApiResponse<UpdateReviewFormResponse> updateReviewForm(
            @PathVariable(name = REVIEW_ID) final Long reviewId
    ) {
        /* 회원 검증 */

        UpdateReviewFormResponse updateReviewFormResponse = reviewService.getReviewUpdateForm(reviewId);

        return success(updateReviewFormResponse);
    }

    @PutMapping("/update/{reviewId}")
    @Operation(summary = UPDATE_REVIEW_SUMMARY, description = UPDATE_REVIEW_DESCRIPTION)
    public ApiResponse<ReviewMassage> updateReview(
            @PathVariable(name = REVIEW_ID) final Long reviewId,
            @RequestBody @Valid final UpdateReviewRequest updateReviewRequest
    ) {
        /* 회원 검증 */

        ReviewMassage reviewMassage = reviewService.updateReview(reviewId, updateReviewRequest);

        return success(reviewMassage);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = DELETE_REVIEW_SUMMARY, description = DELETE_REVIEW_DESCRIPTION)
    public ApiResponse<ReviewMassage> deleteReview(
            @PathVariable(name = REVIEW_ID) Long reviewId
    ) {
        /* 회원 검증 + 해당 리뷰를 작성한 회원이 맞는지(컨트롤러에서? 서비스에서? -> userService에서 검증 */

        ReviewMassage reviewMassage = reviewService.deleteReview(reviewId);

        return success(reviewMassage);
    }
}
