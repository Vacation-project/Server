package Vacationproject.shoppingMall.domain.review.exception;

import Vacationproject.shoppingMall.common.Error.exception.BusinessException;
import Vacationproject.shoppingMall.common.Error.exception.ErrorCode;

public class ReviewException extends BusinessException {
    public ReviewException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ReviewException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
