package Vacationproject.shoppingMall.domain.product.dto;

import Vacationproject.shoppingMall.common.constant.ConstraintConstants;
import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.model.ProductImage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.*;


public class ProductDto {

    /**
     * Request
     */
    @Builder
    public record CreateProductRequest(
            @NotNull
            String productName,
            @NotNull
            @Size(min = PRODUCT_PRICE_MIN)
            int productPrice,
            @NotNull
            @Size(min = PRODUCT_QUANTITY_MIN_SIZE)
            int stockQuantity,
            @NotNull
            @Size(min = PRODUCT_CONTENT_MIN_SIZE)
            String content,
//            List<String> imageUrls //TODO image가 MutipartFile로 넘어오는게 맞는지, String으로 넘어와야 하는지 고민
            @NotNull
            List<MultipartFile> images
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
            String productName,
            @NotNull
            @Size(min = PRODUCT_PRICE_MIN)
            int productPrice,
            @NotNull
            @Size(min = PRODUCT_QUANTITY_MIN_SIZE)
            int stockQuantity,
            @NotNull
            @Size(min = ConstraintConstants.PRODUCT_CONTENT_MIN_SIZE)
            String productComment,
//            @NotNull
//            List<String> imageUrls,
            @NotNull
            List<MultipartFile> images,
            @NotNull
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
            String name,
            int price,
            int stockQuantity,
            String content,
            List<String> imageUrl
    ) {
        public static ProductUpdateResponse of(Product product) {
            return ProductUpdateResponse.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .stockQuantity(product.getStockQuantity())
                    .content(product.getContent())
                    .imageUrl(product.getProductImageList().stream().map(ProductImage::getImageUrl).toList())
                    .build();
        }
    }

}
