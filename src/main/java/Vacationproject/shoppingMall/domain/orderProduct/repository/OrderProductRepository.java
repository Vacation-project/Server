package Vacationproject.shoppingMall.domain.orderProduct.repository;

import Vacationproject.shoppingMall.domain.orderProduct.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
