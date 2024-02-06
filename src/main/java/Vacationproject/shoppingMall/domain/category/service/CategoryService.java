package Vacationproject.shoppingMall.domain.category.service;

import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public String createCategory(String categoryName) {
        Category category = Category.builder()
                .name(categoryName)
                .build();
        categoryRepository.save(category);

        return categoryName;
    }
}
