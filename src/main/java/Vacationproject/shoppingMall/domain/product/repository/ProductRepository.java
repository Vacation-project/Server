package Vacationproject.shoppingMall.domain.product.repository;

import Vacationproject.shoppingMall.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.productImageList WHERE p.category.id = :categoryId")
    Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    Page<Product> findByCategoryIdAndIdNot(Long categoryId, Long productId, Pageable pageable);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.productImageList WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> findByNameContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);

    Page<Product> findByNameContaining(@Param("keyword") String keyword, Pageable pageable);

    boolean existsByName(String name);
}
