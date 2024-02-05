package Vacationproject.shoppingMall.domain.product.api;

import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static Vacationproject.shoppingMall.common.dto.ApiResponse.success;
import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;

    /**
     * 상품 생성
     * TODO 회원 기능이 개발된다면 authService를 사용해서 현재 로그인 했는지, admin인지 검증 후 실행되도록 변경
     * 만약 로그인 X or Admin X인 경우 예외 발생
     */
    @PostMapping("/{categoryId}/admin")
    public ApiResponse<ProductMessage> createProduct(
            @RequestBody @Valid final CreateProductRequest createProductRequest,
//                                     @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable(name = "categoryId") final Long categoryId) throws IOException {
        /* 로그인한 유저가 어드민이 맞는지 검증 */
        // authService.checkIsAdmin(principalDetails.getUser())

        ProductMessage message = productService.createProduct(createProductRequest, categoryId);
        return success(message);
    }

    /**
     * 상품 상세 페이지
     */
    @GetMapping("/{productId}")
    public ApiResponse<ProductDetailResponse> getProduct(
            @PathVariable(name = "productId") final Long productId,
            @PageableDefault(page = 0, size=4, sort = "id", direction = Sort.Direction.DESC) final Pageable pageable
    ) {
        final ProductDetailResponse productDetailResponse = productService.getProductAndReview(productId, pageable);
        return success(productDetailResponse);
    }

    /**
     * 상품 수정
     */
    @GetMapping("/{productId}/admin")
    public ApiResponse<ProductUpdateResponse> updateProductForm(
            @PathVariable(name = "productId") final Long productId
//            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        // authService.checkIsAdmin(principalDetails.getUser())
        final ProductUpdateResponse productUpdateResponse = productService.getProduct(productId);

        return success(productUpdateResponse);
    }

    @PutMapping("/{productId}/admin")
    public ApiResponse<ProductMessage> updateProduct(
            @PathVariable(name = "productId") final Long productId,
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
    public ApiResponse<ProductMessage> deleteProduct(
            @PathVariable(name = "productId") final Long productId
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
