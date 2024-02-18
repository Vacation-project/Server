package Vacationproject.shoppingMall.domain.product.service;

import Vacationproject.shoppingMall.domain.category.model.Category;
import Vacationproject.shoppingMall.domain.category.service.CategoryService;
import Vacationproject.shoppingMall.domain.product.exception.ProductException;
import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.model.ProductImage;
import Vacationproject.shoppingMall.domain.product.repository.ProductQueryRepository;
import Vacationproject.shoppingMall.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static Vacationproject.shoppingMall.common.Error.exception.ErrorCode.PRODUCT_NAME_DUPLICATION;
import static Vacationproject.shoppingMall.common.Error.exception.ErrorCode.PRODUCT_NOT_FOUND;
import static Vacationproject.shoppingMall.domain.product.dto.ProductDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final CategoryService categoryService;
    private final ImageStore imageStore;

    public Product getProduct(final Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(PRODUCT_NOT_FOUND));
    }

    public List<HomeProductResponse> getMainPageProduct(Pageable pageable, String sortKey) {
        Pageable updatePageable = pagingCondition(pageable, sortKey);
        Page<Product> products = productRepository.findMainPageProduct(updatePageable);

        return products.stream().map(HomeProductResponse::of).toList();
    }

    @Transactional
    // 상품 생성
    public ProductMessage createProduct(final CreateProductRequest createProductRequest, final Long categoryId, List<MultipartFile> images) throws IOException {
        nameDuplicationCheck(createProductRequest.productName());

        final Category category = categoryService.getCategory(categoryId);
        final Product product = productRepository.save(createProductRequest.toEntity(category));

        imageStore.storeFiles(images).forEach(imageUrl ->
                product.addProductImage(ProductImage.of(product, imageUrl)));

        return new ProductMessage(true);
    }

    @Transactional
    public ProductMessage deleteProduct(final Long productId) {
        final Product product = getProduct(productId);
        productRepository.delete(product);

        return new ProductMessage(true);
    }


    public ProductUpdateResponse getModifyProduct(final Long productId) {
        final Product product = getProduct(productId);

        return ProductUpdateResponse.of(product);
    }

    @Transactional
    public ProductMessage updateProduct(final Long productId, final UpdateProductRequest updateProduct, List<MultipartFile> images) throws IOException {
        Product product = getProduct(productId);

        nameDuplicationCheck(updateProduct.productName());

        updateProductInf(updateProduct, images, product, categoryService.getCategory(updateProduct.productCategoryId()));

        return new ProductMessage(true);
    }

    private void updateProductInf(UpdateProductRequest updateProduct, List<MultipartFile> images, Product product, Category category) throws IOException {
        if (images == null) { // 이미지는 업데이트 안 한 경우
            /*Dirty Checking*/
            product.update(
                    updateProduct,
                    category);
        } else { // 이미지를 업데이트 한 경우
            final List<String> imageUrls = imageStore.storeFiles(images);
            /*Dirty Checking*/
            product.updateWithImage(
                    updateProduct,
                    imageUrls.stream().map(it -> ProductImage.of(product, it)).toList(),
                    category);
        }
    }

    public ProductDetailResponse getProductWithReviewAndRelationProducts(final int offset, final int limit, final Long productId) {
        final Product product = getProduct(productId);

        final List<Product> products = productQueryRepository.findProductWithCategoryAndImages(offset, limit, productId);
        return ProductDetailResponse.of(product, products);
    }

    public List<CategoryProductResponse> getCategoryProducts(final Long categoryId, final Pageable pageable, final String sortKey) {
        final Pageable updatePageable = pagingCondition(pageable, sortKey);

        final Page<Product> products = productRepository.findByCategoryId(categoryId, updatePageable);

        return products.stream().map(CategoryProductResponse::of).toList();
    }

    public List<SearchProductResponse> getSearchProduct(final String keyword, final Pageable pageable, final String sortKey) {
        final Pageable updatePageable = pagingCondition(pageable, sortKey);

        final Page<Product> products = productRepository.findByNameContainingIgnoreCase(keyword, updatePageable);

        return products.stream().map(SearchProductResponse::of).toList();
    }

    private Pageable pagingCondition(final Pageable pageable, final String sortKey) {
        Sort sort;
        switch (sortKey) {
            case "highPrice":
                sort = Sort.by(Sort.Direction.DESC, "price");
                break;
            case "lowestPrice":
                sort = Sort.by("price");
                break;
            case "popular":
                sort = Sort.by(Sort.Direction.DESC, "favoriteCount");
                break;
            default:
                sort = Sort.by(Sort.Direction.DESC, "createdAt");
                break;
        }
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private void nameDuplicationCheck(final String name) {
        if (productRepository.existsByName(name)) {
            throw new ProductException(PRODUCT_NAME_DUPLICATION);
        }
    }
}
