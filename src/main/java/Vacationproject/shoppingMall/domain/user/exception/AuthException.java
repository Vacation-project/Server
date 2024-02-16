package Vacationproject.shoppingMall.domain.user.exception;

import Vacationproject.shoppingMall.common.exception.shopException;

import java.util.HashMap;

public class AuthException extends shopException {
    public Object data;

    public AuthException(StatusCode statusCode) {
        super(statusCode);
        this.data = new HashMap<String,String>();
    }
    public AuthException(StatusCode statusCode, String message) {
        super(statusCode, message);
        this.data= new HashMap<String, String>();
    }
    public AuthException(StatusCode statusCode, Object data) {
        super(statusCode);
        this.data = data;
    }
}