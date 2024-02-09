package Vacationproject.shoppingMall.domain.product.repository;

import Vacationproject.shoppingMall.domain.product.model.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {
    private final EntityManager em;

    public List<Product> findProductWithCategory(int offset, int limit, Long productId) {
        return em.createQuery(
                        "select p from Product p " +
                                "join fetch p.category c " +
                                "join fetch p.productImageList i " +
                                "where p.id != :excludedProductId " +
                                "order by p.createdAt desc", Product.class)
                .setParameter("excludedProductId", productId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
