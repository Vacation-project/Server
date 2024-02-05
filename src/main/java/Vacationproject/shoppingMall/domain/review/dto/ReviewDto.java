package Vacationproject.shoppingMall.domain.review.dto;

import Vacationproject.shoppingMall.domain.review.model.Review;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewDto {
    /**
     * Request
     */

    /**
     * Response
     */
    @Builder
    public record ReviewResponse(
            String userNickName,
            String reviewTitle,
            String reviewComment,
            LocalDateTime reviewDate,
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

