package Vacationproject.shoppingMall.domain.category.api;

import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryRepository categoryRepository;

    @PostMapping
    public String createCategory(@RequestParam("categoryName") String categoryName) {
        Category category = Category.builder()
                .name(categoryName)
                .build();
        categoryRepository.save(category);
        return categoryName;
    }
}
