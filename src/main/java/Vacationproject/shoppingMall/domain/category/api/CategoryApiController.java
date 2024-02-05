package Vacationproject.shoppingMall.domain.category.api;

import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryRepository categoryRepository;
    @PostMapping
    public String createCategory() {
        Category category = Category.builder()
                .name("상의")
                .build();
        categoryRepository.save(category);
        return "상의";
    }
}
