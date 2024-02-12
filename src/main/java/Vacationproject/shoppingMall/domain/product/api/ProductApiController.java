package Vacationproject.shoppingMall.domain.product.api;

import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.*;
import static Vacationproject.shoppingMall.common.dto.ApiResponse.success;
import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = TAG_PRODUCT, description = TAG_PRODUCT_DESCRIPTION)
public class ProductApiController {
    private final ProductService productService;

    /**
     * 상품 생성
     * TODO 회원 기능이 개발된다면 authService를 사용해서 현재 로그인 했는지, admin인지 검증 후 실행되도록 변경
     * 만약 로그인 X or Admin X인 경우 예외 발생
     *
     */
    @PostMapping("/{categoryId}/admin")
    @Operation(summary = CREATE_PRODUCT_SUMMARY, description = CREATE_PRODUCT_DESCRIPTION)
    public ApiResponse<ProductMessage> createProduct(
            @RequestPart(value = "createProductRequest") @Valid final CreateProductRequest createProductRequest,
            @NotNull @RequestPart(value = "images") List<MultipartFile> images,
//                                     @AuthenticationPrincipal PrincipalDetails principalDetails,
            @Parameter(name = CATEGORY_ID, description = CATEGORY_ID_DESCRIPTION, in = ParameterIn.PATH) @PathVariable(name = CATEGORY_ID) final Long categoryId) throws IOException {
        /* 로그인한 유저가 어드민이 맞는지 검증 */
        // authService.checkIsAdmin(principalDetails.getUser())

        ProductMessage message = productService.createProduct(createProductRequest, categoryId, images);
        return success(message);
    }

    /**
     * 상품 상세 페이지
     */
    @GetMapping("/{productId}")
    @Operation(summary = DETAIL_PRODUCT_SUMMARY, description = DETAIL_PRODUCT_DESCRIPTION)
    public ApiResponse<ProductDetailResponse> getProduct(
            @Parameter(name = PRODUCT_ID, description = PRODUCT_ID_DESCRIPTION, in = ParameterIn.PATH) @PathVariable(name = PRODUCT_ID) final Long productId,
//            @Parameter(name = "상품 페이징 정보", description = "전송하지 않아도 됩니다.") @PageableDefault(page = 0, size=4, sort = "id", direction = Sort.Direction.DESC) final Pageable pageable
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "5") int limit
    ) {
        final ProductDetailResponse productDetailResponse = productService.getProductWithReviewAndRelationProducts(offset, limit, productId);
        return success(productDetailResponse);
    }

    /**
     * 상품 수정
     */
    @GetMapping("/{productId}/admin")
    @Operation(summary = UPDATE_PRODUCT_FORM_SUMMARY, description = UPDATE_PRODUCT_FORM_DESCRIPTION)
    public ApiResponse<ProductUpdateResponse> updateProductForm(
            @Parameter(name = PRODUCT_ID, description = PRODUCT_ID_DESCRIPTION, in = ParameterIn.PATH) @PathVariable(name = PRODUCT_ID) final Long productId
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        // authService.checkIsAdmin(principalDetails.getUser())
        final ProductUpdateResponse productUpdateResponse = productService.getModifyProduct(productId);

        return success(productUpdateResponse);
    }

    @PutMapping("/{productId}/admin")
    @Operation(summary = UPDATE_PRODUCT_SUMMARY, description = UPDATE_PRODUCT_DESCRIPTION)
    public ApiResponse<ProductMessage> updateProduct(
            @Parameter(name = PRODUCT_ID, description = PRODUCT_ID_DESCRIPTION, in = ParameterIn.PATH) @PathVariable(name = PRODUCT_ID) final Long productId,
            @RequestPart(value = "updateProductRequest") @Valid final UpdateProductRequest updateProductRequest,
            @RequestPart(value = "images", required = false) @Nullable List<MultipartFile> images //이미지를 업데이트 하지 않을 경우, 기존 이미지 사용
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws IOException {
        // authService.checkIsAdmin(principalDetails.getUser())
        ProductMessage message = productService.updateProduct(productId, updateProductRequest, images);

        return success(message);
    }

    /**
     * 검색 결과에 따른 상품 조회
     */
    @GetMapping("/search")
    @Operation(summary = SEARCH_PRODUCT_SUMMARY, description = SEARCH_PRODUCT_DESCRIPTION)
    public ApiResponse<List<SearchProductResponse>> searchProduct(
            @RequestParam(name = "keyword", defaultValue = "") @Nullable String keyword,
            @Parameter(name = PAGING, description = PAGING_DESCRIPTION) @PageableDefault(page = 0, size = 30) @Nullable final Pageable pageable,
            @Parameter(name = SORT_KEY, description = SORT_KEY_DESCRIPTION)
            @RequestParam(name = SORT_KEY, defaultValue = "createdAt") @Nullable final String sortKey
    ) {
        List<SearchProductResponse> searchProductResponse = productService.getSearchProduct(keyword, pageable, sortKey);

        return success(searchProductResponse);
    }

    /**
     * 카테고리별 상품 목록 조회
     */
    @GetMapping
    @Operation(summary = CATEGORY_PRODUCT_SUMMARY, description = CATEGORY_PRODUCT_DESCRIPTION)
    public ApiResponse<List<CategoryProductResponse>> getCategoryProduct(
            @Parameter(name = CATEGORY_ID, description = CATEGORY_ID_DESCRIPTION) @RequestParam(name = CATEGORY_ID) final Long categoryId,
            @Parameter(name = PAGING, description = PAGING_DESCRIPTION) @PageableDefault(page = 0, size = 3) @Nullable final Pageable pageable,
            @Parameter(name = SORT_KEY, description = SORT_KEY_DESCRIPTION)
            @RequestParam(name = SORT_KEY, defaultValue = "createdAt") @Nullable final String sortKey
    ) {
        List<CategoryProductResponse> categoryProductResponseList = productService.getCategoryProducts(categoryId, pageable, sortKey);

        return success(categoryProductResponseList);
    }

    /**
     * 상품 삭제
     */
    @DeleteMapping("/{productId}/admin")
    @Operation(summary = DELETE_PRODUCT_SUMMARY, description = DELETE_PRODUCT_DESCRIPTION)
    public ApiResponse<ProductMessage> deleteProduct(
            @Parameter(name = PRODUCT_ID, description = PRODUCT_ID_DESCRIPTION, in = ParameterIn.PATH) @PathVariable(name = PRODUCT_ID) final Long productId
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        // authService.checkIsAdmin(principalDetails.getUser())
        final ProductMessage message = productService.deleteProduct(productId);

        return success(message);
    }
}
