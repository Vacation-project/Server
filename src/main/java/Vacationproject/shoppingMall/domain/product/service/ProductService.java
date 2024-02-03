package Vacationproject.shoppingMall.domain.product.service;

import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.category.repository.CategoryRepository;
import Vacationproject.shoppingMall.domain.product.dto.ProductDto;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.model.ProductImage;
import Vacationproject.shoppingMall.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    // 상품 생성
    public void createProduct(CreateProductRequest createProductRequest, Long categoryId) {
        //TODO 추후 통합 예외 생성 후 변경 예정
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException());
        Product product = productRepository.save(createProductRequest.toEntity(category));

        createProductRequest.imageUrls().forEach(imageUrl ->
                product.addProductImage(ProductImage.of(product, imageUrl)));
    }
}
