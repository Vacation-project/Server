package Vacationproject.shoppingMall.domain.product.dto;

import Vacationproject.shoppingMall.common.constant.ConstraintConstants;
import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.model.ProductImage;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.*;
import static Vacationproject.shoppingMall.domain.review.dto.ReviewDto.ReviewResponse;


public class ProductDto {

    /**
     * Request
     */
    @Builder
    public record CreateProductRequest(
            @NotNull
            @Schema(description = PRODUCT_NAME, nullable = false)
            String productName,
            @NotNull
            @Min(value = PRODUCT_PRICE_MIN)
            @Schema(description = PRODUCT_PRICE, nullable = false)
            int productPrice,
            @NotNull
            @Min(value = PRODUCT_QUANTITY_MIN)
            @Schema(description = PRODUCT_STOCK_QUANTITY, nullable = false)
            int stockQuantity,
            @NotNull
            @Size(min = PRODUCT_CONTENT_MIN_SIZE)
            @Schema(description = PRODUCT_CONTENT, nullable = false)
            String content
    ) {
        public Product toEntity(Category category) {
            return Product.builder()
                    .name(productName)
                    .price(productPrice)
                    .stockQuantity(stockQuantity)
                    .content(content)
                    .category(category)
                    .build();
        }

    }

    @Builder
    public record UpdateProductRequest(
            @NotNull
            @Schema(description = PRODUCT_NAME, nullable = false)
            String productName,
            @NotNull
            @Min(PRODUCT_PRICE_MIN)
            @Schema(description = PRODUCT_PRICE, nullable = false)
            int productPrice,
            @NotNull
            @Min(PRODUCT_QUANTITY_MIN)
            @Schema(description = PRODUCT_STOCK_QUANTITY, nullable = false)
            int stockQuantity,
            @NotNull
            @Size(min = ConstraintConstants.PRODUCT_CONTENT_MIN_SIZE)
            @Schema(description = PRODUCT_CONTENT, nullable = false)
            String productContent,

            @NotNull
            @Schema(description = PRODUCT_CATEGORY_ID, nullable = false)
            Long productCategoryId
    ) {

    }


    /**
     * Response
     */

    public record ProductMessage(
            boolean result
    ) {

    }

    @Builder
    public record ProductUpdateResponse(
            @Schema(description = PRODUCT_NAME)
            String productName,
            @Schema(description = PRODUCT_PRICE)
            int productPrice,
            @Schema(description = PRODUCT_STOCK_QUANTITY)
            int stockQuantity,
            @Schema(description = PRODUCT_CONTENT)
            String productContent,
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls
    ) {
        public static ProductUpdateResponse of(Product product) {
            return ProductUpdateResponse.builder()
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .stockQuantity(product.getStockQuantity())
                    .productContent(product.getContent())
                    .imageUrls(product.getProductImageList().stream().map(ProductImage::getImageUrl).toList())
                    .build();
        }
    }


    // TODO 추후 성능 개선을 위해 리팩토링 예정
    @Builder
    public record ProductDetailResponse(
            @Schema(description = PRODUCT_ID)
            Long productId,
            @Schema(description = PRODUCT_NAME)
            String productName,
            @Schema(description = PRODUCT_PRICE)
            int productPrice,
            @Schema(description = PRODUCT_STOCK_QUANTITY)
            int stockQuantity,
            @Schema(description = PRODUCT_CONTENT)
            String productContent,
            @Schema(description = PRODUCT_CATEGORY_ID)
            Long categoryId, //연관상품을 조회할 때 필요하기 때문에 추가
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls,
            @Schema(description = PRODUCT_REVIEWS)
            List<ReviewResponse> reviewList,
            @Schema(description = PRODUCT_RELATION_PRODUCTS)
            List<RelationProduct> relationProductList
    ) {
        public static ProductDetailResponse of(Product product, List<Product> products) {
            return ProductDetailResponse.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .stockQuantity(product.getStockQuantity())
                    .productContent(product.getContent())
                    .categoryId(product.getCategory().getId())
                    .imageUrls(product.getProductImageList().stream().map(ProductImage::getImageUrl).toList())
                    .reviewList(product.getReviewList().stream().map(ReviewResponse::of).toList())
                    .relationProductList(products.stream().map(RelationProduct::of).toList())
                    .build();
        }
    }

    @Builder
    public record CategoryProductResponse(
            @Schema(description = PRODUCT_ID)
            Long productId,
            @Schema(description = PRODUCT_NAME)
            String productName,
            @Schema(description = PRODUCT_PRICE)
            int productPrice,
            @Schema(description = PRODUCT_CREATE_TIME)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT_YYYY_MM_DD_HH_MM)
            LocalDateTime productCreateTime,
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls
    ) {
        public static CategoryProductResponse of(Product product) {
            return CategoryProductResponse.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .productCreateTime(product.getCreatedAt())
                    .imageUrls(product.getProductImageList().stream().map(ProductImage::getImageUrl).toList())
                    .build();
        }
    }

    @Builder
    public record RelationProduct(
            @Schema(description = PRODUCT_ID)
            Long productId,
            @Schema(description = PRODUCT_NAME)
            String productName,
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls
    ) {
        public static RelationProduct of(Product product) {
            return RelationProduct.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .imageUrls(product.getProductImageList().stream().map(ProductImage::getImageUrl).toList())
                    .build();
        }
    }

    @Builder
    public record SearchProductResponse(
            @Schema(description = PRODUCT_ID)
            Long productId,
            @Schema(description = PRODUCT_NAME)
            String productName,
            @Schema(description = PRODUCT_PRICE)
            int productPrice,
            @Schema(description = PRODUCT_CREATE_TIME)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT_YYYY_MM_DD_HH_MM)
            LocalDateTime productCreateTime,
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls
    ) {
        public static SearchProductResponse of(Product product) {
            return SearchProductResponse.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .productCreateTime(product.getCreatedAt())
                    .imageUrls(product.getProductImageList().stream().map(ProductImage::getImageUrl).toList())
                    .build();
        }
    }

    @Builder
    public record UserFavoriteProductResponse(
            @Schema(description = PRODUCT_ID)
            Long productId,
            @Schema(description = PRODUCT_NAME)
            String productName,
            @Schema(description = PRODUCT_PRICE)
            int productPrice,
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls
    ) {
        public static UserFavoriteProductResponse of(Product product) {
            return UserFavoriteProductResponse.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .imageUrls(product.getProductImageList().stream()
                            .map(ProductImage::getImageUrl).toList())
                    .build();
        }
    }


    @Builder
    public record HomeProductResponse(
            @Schema(description = PRODUCT_ID)
            Long productId,
            @Schema(description = PRODUCT_NAME)
            String productName,
            @Schema(description = PRODUCT_PRICE)
            int productPrice,
            @Schema(description = PRODUCT_CREATE_TIME)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT_YYYY_MM_DD_HH_MM)
            LocalDateTime productCreateTime,
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls
    ) {
        public static HomeProductResponse of(Product product) {
            return HomeProductResponse.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .productCreateTime(product.getCreatedAt())
                    .imageUrls(product.getProductImageList().stream().map(
                            ProductImage::getImageUrl
                    ).toList())
                    .build();
        }
    }
}
