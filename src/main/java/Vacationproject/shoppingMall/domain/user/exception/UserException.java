package Vacationproject.shoppingMall.domain.user.exception;


import Vacationproject.shoppingMall.common.Error.exception.BusinessException;
import Vacationproject.shoppingMall.common.Error.exception.ErrorCode;
// import Vacationproject.shoppingMall.common.exception.shopException;

public class UserException extends BusinessException {
    public UserException(ErrorCode ErrorCode) {
        super(ErrorCode);
    }

    public UserException(ErrorCode ErrorCode, String message) {
        super(message, ErrorCode);
    }
}
