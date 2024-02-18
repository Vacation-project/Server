package Vacationproject.shoppingMall.domain.orderProduct.dto;

import Vacationproject.shoppingMall.domain.orderProduct.model.OrderProduct;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.model.ProductImage;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

import static Vacationproject.shoppingMall.common.constant.ConstraintConstants.*;

public class OrderProductDto {
    /**
     * Request
     */

    /**
     * Response
     */
    @Builder
    public record NotWrittenReviewOrderProduct(
            @Schema(description = ORDER_PRODUCT_ID)
            Long orderProductId,
            @Schema(description = PRODUCT_ID)
            String productName,
            @Schema(description = PRODUCT_PRICE)
            int productPrice,
            @Schema(description = ORDER_PRODUCT_QUANTITY)
            int quantity,
            @Schema(description = ORDER_PRODUCT_ORDER_DATE)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT_YYYY_MM_DD)
            LocalDateTime orderDateTime,
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls
    ) {
        public static NotWrittenReviewOrderProduct of(OrderProduct orderProduct, Product product) {
            return NotWrittenReviewOrderProduct.builder()
                    .orderProductId(orderProduct.getId())
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .quantity(orderProduct.getQuantity())
                    .orderDateTime(orderProduct.getCreatedAt())
                    .imageUrls(product.getProductImageList().stream()
                            .map(ProductImage::getImageUrl).toList())
                    .build();
        }
    }

    @Builder
    public record OrderProductReviewResponse(
            @Schema(name = ORDER_PRODUCT_ID)
            Long orderProductId,
            @Schema(name = PRODUCT_NAME)
            String ProductName
    ) {
        public static OrderProductReviewResponse of(OrderProduct orderProduct) {
            return OrderProductReviewResponse.builder()
                    .orderProductId(orderProduct.getId())
                    .ProductName(orderProduct.getProduct().getName())
                    .build();
        }
    }
}
