package Vacationproject.shoppingMall.domain.category.repository;

import Vacationproject.shoppingMall.domain.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
