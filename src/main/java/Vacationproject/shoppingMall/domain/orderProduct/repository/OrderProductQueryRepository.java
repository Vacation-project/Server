package Vacationproject.shoppingMall.domain.orderProduct.repository;

import Vacationproject.shoppingMall.domain.orderProduct.model.OrderProduct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderProductQueryRepository {
    private final EntityManager em;

    /*회원이 구매한 orderProduct 중 리뷰를 작성하지 않은 OrderProduct 조회*/
    public List<OrderProduct> findNotWrittenReviewOrderProducts(Long userId) {
        return em.createQuery(
                        "select op from OrderProduct op " +
                                "join fetch op.product p " +
                                "join fetch p.productImageList pi " +
                                "join fetch op.order o " +  // 여기서의 join fetch는 필요하지 않습니다.
                                "join fetch o.user u " +   // 없어도 됨
                                "where op.reviewCheck = false and u.id = :userId", OrderProduct.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
