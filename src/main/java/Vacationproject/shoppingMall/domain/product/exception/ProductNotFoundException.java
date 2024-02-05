package Vacationproject.shoppingMall.domain.product.exception;

import Vacationproject.shoppingMall.common.Error.exception.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(Long target) {
        super(target + " is not found");
    }
}
