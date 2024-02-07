package Vacationproject.shoppingMall.domain.product.service;

import Vacationproject.shoppingMall.domain.category.exception.CategoryNotFoundException;
import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.category.repository.CategoryRepository;
import Vacationproject.shoppingMall.domain.product.exception.ProductException;
import Vacationproject.shoppingMall.domain.product.exception.ProductNotFoundException;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.model.ProductImage;
import Vacationproject.shoppingMall.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static Vacationproject.shoppingMall.common.Error.exception.ErrorCode.PRODUCT_NAME_DUPLICATION;
import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageStore imageStore;

    @Transactional
    // 상품 생성
    public ProductMessage createProduct(final CreateProductRequest createProductRequest, final Long categoryId, List<MultipartFile> images) throws IOException {
        nameDuplicationCheck(createProductRequest.productName());

        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        final Product product = productRepository.save(createProductRequest.toEntity(category));

        imageStore.storeFiles(images).forEach(imageUrl ->
                product.addProductImage(ProductImage.of(product, imageUrl)));

        return new ProductMessage(true);
    }

    @Transactional
    public ProductMessage deleteProduct(final Long productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        productRepository.delete(product);

        return new ProductMessage(true);
    }


    public ProductUpdateResponse getProduct(final Long productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return ProductUpdateResponse.of(product);
    }

    @Transactional
    public ProductMessage updateProduct(final Long productId, final UpdateProductRequest updateProduct) throws IOException {
        nameDuplicationCheck(updateProduct.productName());

        final Long categoryId = updateProduct.productCategoryId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        final List<String> imageUrls = imageStore.storeFiles(updateProduct.images());

        /*Dirty Checking 발생*/
        product.update(
                updateProduct,
                imageUrls.stream().map(it -> ProductImage.of(product, it)).toList(),
                category);

        return new ProductMessage(true);
    }

    public ProductDetailResponse getProductAndReview(final Long productId, final Pageable pageable) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        final Page<Product> products = productRepository.findByCategoryId(product.getCategory().getId(), pageable);

        return ProductDetailResponse.of(product, products);
    }

    private void nameDuplicationCheck(String name) {
        if (productRepository.existsByName(name)){
            throw new ProductException(PRODUCT_NAME_DUPLICATION);
        }
    }
}
