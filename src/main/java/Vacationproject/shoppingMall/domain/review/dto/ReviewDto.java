package Vacationproject.shoppingMall.domain.review.dto;

import Vacationproject.shoppingMall.domain.review.model.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.*;

public class ReviewDto {
    /**
     * Request
     */

    /**
     * Response
     */
    @Builder
    public record ReviewResponse(
            @Schema(description = REVIEW_USER_NICKNAME)
            String userNickName,
            @Schema(description = REVIEW_TITLE)
            String reviewTitle,
            @Schema(description = REVIEW_COMMENT)
            String reviewComment,
            @Schema(description = REVIEW_CREATE_DATE)
            LocalDateTime reviewDate,
            @Schema(description = REVIEW_RATING)
            int rating
    ) {
        public static ReviewResponse of(Review review) {
            return ReviewResponse.builder()
                    .userNickName(review.getUser().getNickName())
                    .reviewTitle(review.getTitle())
                    .reviewComment(review.getComment())
                    .reviewDate(review.getCreatedAt())
                    .rating(review.getRating())
                    .build();
        }
    }
}

