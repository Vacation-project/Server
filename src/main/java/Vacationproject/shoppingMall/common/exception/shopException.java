package Vacationproject.shoppingMall.common.exception;

public class shopException extends RuntimeException {
    public StatusCode statusCode;
    public shopException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode=statusCode;
    }

    public shopException(StatusCode statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}