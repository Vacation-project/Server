package Vacationproject.shoppingMall.domain.category.service;

import Vacationproject.shoppingMall.domain.category.dto.CategoryDto;
import Vacationproject.shoppingMall.domain.category.exception.CategoryException;
import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static Vacationproject.shoppingMall.common.Error.exception.ErrorCode.CATEGORY_NAME_DUPLICATION;
import static Vacationproject.shoppingMall.common.Error.exception.ErrorCode.CATEGORY_NOT_FOUND;
import static Vacationproject.shoppingMall.domain.category.dto.CategoryDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).
                orElseThrow(() -> new CategoryException(CATEGORY_NOT_FOUND));
    }

    public String createCategory(String categoryName) {
        nameDuplicationCheck(categoryName);

        Category category = Category.builder()
                .name(categoryName)
                .build();
        categoryRepository.save(category);

        return categoryName;
    }

    private void nameDuplicationCheck(String name) {
        if (categoryRepository.existsByName(name)){
            throw new CategoryException(CATEGORY_NAME_DUPLICATION);
        }
    }

    public List<CategoryListResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryDto.CategoryListResponse::of).toList();
    }
}
