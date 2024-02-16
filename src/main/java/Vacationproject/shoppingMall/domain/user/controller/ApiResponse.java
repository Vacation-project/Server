package Vacationproject.shoppingMall.domain.user.controller;


public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    // ApiResponse 생성자
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // 성공 시 사용할 정적 팩토리 메서드
    public static <T> Vacationproject.shoppingMall.domain.product.api.ApiResponse<T> success(T data) {
        return new Vacationproject.shoppingMall.domain.product.api.ApiResponse<>(true, "Operation Successful", data);
    }

    public static <T> Vacationproject.shoppingMall.domain.product.api.ApiResponse<T> success(String message, T data) {
        return new Vacationproject.shoppingMall.domain.product.api.ApiResponse<>(true, message, data);
    }

    // 에러 시 사용할 정적 팩토리 메서드
    public static Vacationproject.shoppingMall.domain.product.api.ApiResponse<?> error(String message) {
        return new Vacationproject.shoppingMall.domain.product.api.ApiResponse<>(false, message, null);
    }

    /**
     * @Getter
     */
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
