package Vacationproject.shoppingMall.domain.category.dto;

import Vacationproject.shoppingMall.domain.category.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.CATEGORY_ID;
import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.CATEGORY_NAME;

public class CategoryDto {

    public record CategoryMessage(
            boolean result
    ) {
    }

    @Builder
    public record CategoryListResponse(
            @Schema(description = CATEGORY_ID)
            Long categoryId,
            @Schema(description = CATEGORY_NAME)
            String categoryName
    ) {
        public static CategoryListResponse of(Category category) {
            return CategoryListResponse.builder()
                    .categoryId(category.getId())
                    .categoryName(category.getName())
                    .build();
        }
    }
}
