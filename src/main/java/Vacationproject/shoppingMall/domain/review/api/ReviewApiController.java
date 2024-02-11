package Vacationproject.shoppingMall.domain.review.api;

import Vacationproject.shoppingMall.common.constant.SwaggerConstants;
import Vacationproject.shoppingMall.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.TAG_REVIEW_DESCRIPTION;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@Tag(name = SwaggerConstants.TAG_REVIEW, description = TAG_REVIEW_DESCRIPTION)
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/user")
    public void notWrittenReviews(
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
//
        reviewService.notWrittenReviews(1L);
    }
}
