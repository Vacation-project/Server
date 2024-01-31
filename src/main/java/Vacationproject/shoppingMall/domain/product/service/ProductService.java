package Vacationproject.shoppingMall.domain.product.service;

import Vacationproject.shoppingMall.domain.product.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    Product findProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);

}
