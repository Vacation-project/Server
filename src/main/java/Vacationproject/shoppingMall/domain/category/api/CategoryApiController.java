package Vacationproject.shoppingMall.domain.category.api;

import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.*;
import static Vacationproject.shoppingMall.common.dto.ApiResponse.success;
import static Vacationproject.shoppingMall.domain.category.dto.CategoryDto.CategoryListResponse;
import static Vacationproject.shoppingMall.domain.category.dto.CategoryDto.CategoryMessage;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Tag(name = TAG_CATEGORY, description = TAG_CATEGORY_DESCRIPTION)
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = CREATE_CATEGORY_SUMMARY, description = CREATE_CATEGORY_DESCRIPTION)
    public ApiResponse<CategoryMessage> createCategory(
            @Parameter(name = CATEGORY_NAME, description = CATEGORY_NAME_DESCRIPTION) @RequestParam(CATEGORY_NAME) final String categoryName) {

        CategoryMessage message = categoryService.createCategory(categoryName);
        return success(message);
    }

    @GetMapping
    @Operation(summary = FIND_CATEGORY_SUMMARY, description = FIND_CATEGORY_DESCRIPTION)
    public ApiResponse<List<CategoryListResponse>> findCategoryList() {

        return ApiResponse.success(categoryService.findAll());
    }

}
