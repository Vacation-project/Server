package Vacationproject.shoppingMall.domain.category.exception;

import Vacationproject.shoppingMall.common.Error.exception.BusinessException;
import Vacationproject.shoppingMall.common.Error.exception.ErrorCode;

public class CategoryException extends BusinessException {

    public CategoryException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CategoryException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
