package Vacationproject.shoppingMall.domain.product.service;

import Vacationproject.shoppingMall.domain.category.exception.CategoryNotFoundException;
import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.category.repository.CategoryRepository;
import Vacationproject.shoppingMall.domain.product.exception.ProductNotFoundException;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.model.ProductImage;
import Vacationproject.shoppingMall.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.CreateProductRequest;
import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.ProductMessage;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    // 상품 생성
    public ProductMessage createProduct(CreateProductRequest createProductRequest, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Product product = productRepository.save(createProductRequest.toEntity(category));

        createProductRequest.imageUrls().forEach(imageUrl ->
                product.addProductImage(ProductImage.of(product, imageUrl)));

        return new ProductMessage(true);
    }

    public ProductMessage deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        productRepository.delete(product);

        return new ProductMessage(true);
    }
}
