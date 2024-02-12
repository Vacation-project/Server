package Vacationproject.shoppingMall.domain.review.dto;

import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.review.model.Review;
import Vacationproject.shoppingMall.domain.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.*;

public class ReviewDto {
    /**
     * Request
     */
    @Builder
    public record ReviewRequest(
            @Size(min = REVIEW_TITLE_MIN_SIZE, max = REVIEW_TITLE_MAX_SIZE)
            String reviewTitle,
            @Size(min = REVIEW_CONTENT_MIN_SIZE, max = REVIEW_CONTENT_MAX_SIZE)
            String reviewContent,
            int reviewRating
    ) {
        public Review toEntity(User user, Product product) {
            return Vacationproject.shoppingMall.domain.review.model.Review.builder()
                    .user(user)
                    .product(product)
                    .title(reviewTitle)
                    .comment(reviewContent)
                    .rating(reviewRating)
                    .build();
        }
    }

    /**
     * Response
     */
    public record ReviewMassage(
            boolean result
    ) {

    }
    @Builder
    public record ReviewResponse(
            @Schema(description = REVIEW_USER_NICKNAME)
            String userNickName,
            @Schema(description = REVIEW_TITLE)
            String reviewTitle,
            @Schema(description = REVIEW_COMMENT)
            String reviewComment,
            @Schema(description = REVIEW_CREATE_DATE)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
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

