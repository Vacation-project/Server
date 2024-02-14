package Vacationproject.shoppingMall.domain.orderProduct.exception;

import Vacationproject.shoppingMall.common.Error.exception.BusinessException;
import Vacationproject.shoppingMall.common.Error.exception.ErrorCode;

public class OrderProductException extends BusinessException {

    public OrderProductException(ErrorCode errorCode) {
        super(errorCode);
    }

    public OrderProductException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
