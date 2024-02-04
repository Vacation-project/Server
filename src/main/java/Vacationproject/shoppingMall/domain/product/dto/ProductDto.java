package Vacationproject.shoppingMall.domain.product.dto;

import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.product.model.Product;
import lombok.Builder;

import java.util.List;


public class ProductDto {

    /**
     * Request
     */
    @Builder
    public record CreateProductRequest(
            String productName,
            int productPrice,
            int stockQuantity,
            String content,
            List<String> imageUrls //TODO image가 MutipartFile로 넘어오는게 맞는지, String으로 넘어와야 하는지 고가
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

    /**
     * Response
     */

    public record ProductMessage(
            boolean result
    ) {

    }

}
