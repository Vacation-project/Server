package Vacationproject.shoppingMall.domain.product.repository;

import Vacationproject.shoppingMall.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 필요한 메서드가 있다면 추가하면 되는데, 아직 없음. 24.01.29
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    boolean existsByName(String name);
}
