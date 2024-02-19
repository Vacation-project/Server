package Vacationproject.shoppingMall.domain.review.dto;

import Vacationproject.shoppingMall.domain.review.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class ReviewInfo {
        private Long reviewId;
        private Long userId;
        private Long productId;
        private String title;
        private String comment;
        private int rating;
        private LocalDateTime reviewDate;
        private String imagePath;
        private String imageName;

        public static ReviewInfo of(Review review) {
            return new ReviewInfo(
                    review.getId(),
                    review.getUser().getId(),
                    review.getProduct().getId(),
                    review.getTitle(),
                    review.getComment(),
                    review.getRating(),
                    review.getCreatedDate(),
                    review.getImagePath(),
                    review.getImageName()
            );
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreateReviewRequest {
        private Long userId;
        private Long productId;
        private String title;
        private String comment;
        private int rating;
        // TODO: imagePath와 imageName은 파일 업로드 구현 시 추가될 수 있움
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class ReviewResponse {
        private String status;
        private String message;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class UpdateReviewRequest {
        private String title;
        private String comment;
        private int rating;
        //  TODO : imagePath와 imageName 수정 기능은 추가 구현이 필요할 수 있음.
    }
}
