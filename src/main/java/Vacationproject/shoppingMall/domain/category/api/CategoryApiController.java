package Vacationproject.shoppingMall.domain.category.api;

import Vacationproject.shoppingMall.common.constant.SwaggerConstants;
import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static Vacationproject.shoppingMall.common.dto.ApiResponse.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Tag(name = SwaggerConstants.TAG_CATEGORY, description = SwaggerConstants.TAG_CATEGORY_DESCRIPTION)
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "카테고리 생성", description = "카테고리를 생성합니다.")
    public ApiResponse<String> createCategory(
            @Parameter(name = "categoryName", description = "Category 의 Name") @RequestParam("categoryName") final String categoryName) {
        return success(categoryService.createCategory(categoryName));
    }
}
