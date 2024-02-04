package Vacationproject.shoppingMall.domain.category.model;

import Vacationproject.shoppingMall.domain.product.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리는 서버를 실행할 때 자동으로 등록되도록 설정할 예정 -> init class를 생성해서 만들고자하는 카테고리 생성
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(cascade = CascadeType.ALL) // 해당 카테고리가 삭제된다면 그에 관련된 모든 상품이 삭제
    private List<Product> productList = new ArrayList<>();
}
