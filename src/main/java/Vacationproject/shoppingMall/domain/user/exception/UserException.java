package Vacationproject.shoppingMall.domain.user.exception;


import Vacationproject.shoppingMall.common.exception.shopException;
import Vacationproject.shoppingMall.common.exception.StatusCode;

public class UserException extends shopException {
    public UserException(StatusCode statusCode) {
        super(statusCode);
    }

    public UserException(StatusCode statusCode, String message) {
        super(statusCode, message);
    }
}

