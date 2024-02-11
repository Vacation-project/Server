package Vacationproject.shoppingMall.domain.review.repository;

import Vacationproject.shoppingMall.domain.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
