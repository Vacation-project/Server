package Vacationproject.shoppingMall.domain.orderProduct.dto;

import Vacationproject.shoppingMall.domain.orderProduct.model.OrderProduct;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.model.ProductImage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

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
            @Schema(description = PRODUCT_IMAGES)
            List<String> imageUrls
    ) {
        public static NotWrittenReviewOrderProduct of(OrderProduct orderProduct, Product product) {
            return NotWrittenReviewOrderProduct.builder()
                    .orderProductId(orderProduct.getId())
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .quantity(orderProduct.getQuantity())
                    .imageUrls(product.getProductImageList().stream()
                            .map(ProductImage::getImageUrl).toList())
                    .build();
        }
    }
}
