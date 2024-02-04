package Vacationproject.shoppingMall.domain.category.exception;

import Vacationproject.shoppingMall.common.Error.exception.EntityNotFoundException;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException(Long target) {
        super(target + " is not found");
    }
}
