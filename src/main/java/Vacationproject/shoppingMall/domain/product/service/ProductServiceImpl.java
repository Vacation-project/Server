package Vacationproject.shoppingMall.domain.product.service;

import Vacationproject.shoppingMall.domain.product.model.Product;
import Vacationproject.shoppingMall.domain.product.repository.ProductRepository;
import Vacationproject.shoppingMall.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //TODO 추가
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    // 이 방법 말고 final 붙으면 자동으로 생성해주는 어노테이션이 있었는데 기억 x -> 알아보기

    @Override
    public List<Product> findAllProducts() {
        return null;
    }

    @Override
    public Product findProductById(Long id) {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
