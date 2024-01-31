package Vacationproject.shoppingMall.domain.etc;

import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 할인
 */
@Getter
//@Entity
@Table(name = "discount")
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product ProductId;

    @OneToOne
    private Category categoryId;

    private int discountRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
