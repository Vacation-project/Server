package Vacationproject.shoppingMall.domain.product.api;

import Vacationproject.shoppingMall.common.constant.SwaggerConstants;
import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static Vacationproject.shoppingMall.common.dto.ApiResponse.success;
import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = SwaggerConstants.TAG_PRODUCT, description = SwaggerConstants.TAG_PRODUCT_DESCRIPTION)
public class ProductApiController {
    private final ProductService productService;

    /**
     * 상품 생성
     * TODO 회원 기능이 개발된다면 authService를 사용해서 현재 로그인 했는지, admin인지 검증 후 실행되도록 변경
     * 만약 로그인 X or Admin X인 경우 예외 발생
     */
    @PostMapping("/{categoryId}/admin")
    @Operation(summary = "상품 등록", description = "상품 정보과 상품 이미지(images)를 이용하여 상품을 신규 등록합니다."
//            , responses = {
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "상품 생성 성공"),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "상품 이름 중복 오류", content = @Content(schema = ErrorResponse.class))}
    )
    public ApiResponse<ProductMessage> createProduct(
            @RequestPart(value = "createProductRequest") @Valid final CreateProductRequest createProductRequest,
            @NotNull @RequestPart(value = "images") List<MultipartFile> images,
//                                     @AuthenticationPrincipal PrincipalDetails principalDetails,
            @Parameter(name = "categoryId", description = "Category 의 id", in = ParameterIn.PATH)@PathVariable(name = "categoryId") final Long categoryId) throws IOException {
        /* 로그인한 유저가 어드민이 맞는지 검증 */
        // authService.checkIsAdmin(principalDetails.getUser())

        ProductMessage message = productService.createProduct(createProductRequest, categoryId, images);
        return success(message);
    }

    /**
     * 상품 상세 페이지
     */
    @GetMapping("/{productId}")
    @Operation(summary = "상품 정보 페이지", description = "ProductId와 일치하는 상품 정보를 가져옵니다.")
    public ApiResponse<ProductDetailResponse> getProduct(
            @Parameter(name = "productId", description = "Product 의 id", in = ParameterIn.PATH) @PathVariable(name = "productId") final Long productId,
            @Parameter(name = "상품 페이징 정보", description = "전송하지 않아도 됩니다.") @PageableDefault(page = 0, size=4, sort = "id", direction = Sort.Direction.DESC) final Pageable pageable
    ) {
        final ProductDetailResponse productDetailResponse = productService.getProductAndReview(productId, pageable);
        return success(productDetailResponse);
    }

    /**
     * 상품 수정
     */
    @GetMapping("/{productId}/admin")
    @Operation(summary = "상품 수정 폼", description = "productId와 일치하는 상품의 정보를 가져옵니다.")
    public ApiResponse<ProductUpdateResponse> updateProductForm(
            @Parameter(name = "productId", description = "Product 의 id", in = ParameterIn.PATH) @PathVariable(name = "productId") final Long productId
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        // authService.checkIsAdmin(principalDetails.getUser())
        final ProductUpdateResponse productUpdateResponse = productService.getProduct(productId);

        return success(productUpdateResponse);
    }

    @PutMapping("/{productId}/admin")
    @Operation(summary = "상품 수정", description = "수정한 상품 정보와 상품 이미지(images)를 이용하여 상품 정보를 수정합니다.")
    public ApiResponse<ProductMessage> updateProduct(
            @Parameter(name = "productId", description = "Product 의 id", in = ParameterIn.PATH) @PathVariable(name = "productId") final Long productId,
            @RequestBody @Valid final UpdateProductRequest updateProductRequest
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws IOException {
        // authService.checkIsAdmin(principalDetails.getUser())
        final ProductMessage message = productService.updateProduct(productId, updateProductRequest);

        return success(message);
    }

    /**
     * 상품 삭제
     */
    @DeleteMapping("/{productId}/admin")
    @Operation(summary = "상품 삭제", description = "ProductId와 일치하는 상품을 삭제합니다.")
    public ApiResponse<ProductMessage> deleteProduct(
            @Parameter(name = "productId", description = "Product 의 id", in = ParameterIn.PATH) @PathVariable(name = "productId") final Long productId
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        // authService.checkIsAdmin(principalDetails.getUser())
        final ProductMessage message = productService.deleteProduct(productId);

        return success(message);
    }

    /**
     * 검색 결과에 따른 상품 리스트
     */

    /**
     * 카테고리별 상품 목록 조정
     */

}
