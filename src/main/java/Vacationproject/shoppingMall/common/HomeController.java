package Vacationproject.shoppingMall.common;

import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.*;
import static Vacationproject.shoppingMall.common.dto.ApiResponse.success;
import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.HomeProductResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = TAG_HOME, description = TAG_HOME_DESCRIPTION)
public class HomeController {
    private final ProductService productService;
    @GetMapping("/")
    @Operation(summary = HOME_SUMMARY, description = HOME_DESCRIPTION)
    public ApiResponse<List<HomeProductResponse>> Home(
            @Parameter(name = PAGING, description = PAGING_DESCRIPTION) @PageableDefault(page = 0, size = 30) @Nullable final Pageable pageable,
            @Parameter(name = SORT_KEY, description = SORT_KEY_DESCRIPTION)
            @RequestParam(name = SORT_KEY, defaultValue = "createdAt") @Nullable final String sortKey) {
        List<HomeProductResponse> homeProductResponses = productService.getMainPageProduct(pageable, sortKey);

        return success(homeProductResponses);
    }
}
