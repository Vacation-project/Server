package Vacationproject.shoppingMall.domain.review.repository;

import Vacationproject.shoppingMall.domain.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 기본 CRUD 메서드와 함께 필요한 쿼리 메서드를 정의하면됨. (JPA 방식_)


}
