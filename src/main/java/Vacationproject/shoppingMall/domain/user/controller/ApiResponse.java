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

    /*// 성공 시 사용할 정적 팩토리 메서드
    public static <T> Vacationproject.shoppingMall.domain.product.api.ApiResponse<T> success(T data) {
        return new Vacationproject.shoppingMall.domain.product.api.ApiResponse<>(true, "Operation Successful", data);
    }

    public static <T> Vacationproject.shoppingMall.domain.product.api.ApiResponse<T> success(String message, T data) {
        return new Vacationproject.shoppingMall.domain.product.api.ApiResponse<>(true, message, data);
    }

    // 에러 시 사용할 정적 팩토리 메서드
    public static Vacationproject.shoppingMall.domain.product.api.ApiResponse<?> error(String message) {
        return new Vacationproject.shoppingMall.domain.product.api.ApiResponse<>(false, message, null);
    }*/

    // ApiResponse 클래스의 정적 팩토리 메서드 수정
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Operation Successful", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static ApiResponse<?> error(String message) {
        return new ApiResponse<>(false, message, null);

    }
}
