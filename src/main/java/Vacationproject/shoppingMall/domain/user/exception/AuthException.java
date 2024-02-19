package Vacationproject.shoppingMall.domain.user.exception;

import Vacationproject.shoppingMall.common.Error.exception.BusinessException;
import Vacationproject.shoppingMall.common.Error.exception.ErrorCode;
//import Vacationproject.shoppingMall.common.exception.shopException;

import java.util.HashMap;

public class AuthException extends BusinessException {
    public Object data;

    public AuthException(ErrorCode errorCode) {
        super(errorCode);
        this.data = new HashMap<String,String>();
    }
    public AuthException(ErrorCode errorCode, String message) {
        super(message, errorCode);
        this.data= new HashMap<String, String>();
    }
    public AuthException(ErrorCode errorCode, Object data) {
        super(errorCode);
        this.data = data;
    }
}