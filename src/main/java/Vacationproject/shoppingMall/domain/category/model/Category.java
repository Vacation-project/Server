package Vacationproject.shoppingMall.domain.category.model;

import Vacationproject.shoppingMall.domain.product.model.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL) // 해당 카테고리가 삭제된다면 그에 관련된 모든 상품이 삭제
    private List<Product> productList = new ArrayList<>();
}
