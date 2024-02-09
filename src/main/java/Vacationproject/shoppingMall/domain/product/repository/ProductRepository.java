package Vacationproject.shoppingMall.domain.product.repository;

import Vacationproject.shoppingMall.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findByCategoryIdAndIdNot(Long categoryId, Long productId, Pageable pageable);

    boolean existsByName(String name);
}
