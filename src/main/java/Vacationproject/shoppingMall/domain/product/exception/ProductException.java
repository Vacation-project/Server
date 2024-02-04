package Vacationproject.shoppingMall.domain.product.exception;

import Vacationproject.shoppingMall.common.Error.exception.BusinessException;
import Vacationproject.shoppingMall.common.Error.exception.ErrorCode;

public class ProductException extends BusinessException {

    public ProductException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ProductException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
