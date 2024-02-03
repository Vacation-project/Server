package Vacationproject.shoppingMall.domain.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    @URL /* 올바른 url 형식이 아니라면 검증 부분에서 에러를 발생시킴 */
    private String imageUrl;

    public static ProductImage of(Product product, String imageUrl) {
        ProductImage productImage = ProductImage.builder()
                .product(product) //연관관계 설정
                .imageUrl(imageUrl)
                .build();

        return productImage;
    }
}
