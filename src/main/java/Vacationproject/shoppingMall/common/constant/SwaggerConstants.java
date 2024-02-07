package Vacationproject.shoppingMall.common.constant;


public class SwaggerConstants {
    /**
     * swagger
     */
    public static final String[] SWAGGER_APPOINTED_PATHS = {
            "/**"
    };
    public static final String DEFINITION_TITLE = "Vacation Projdct API 명세서";
    public static final String DEFINITION_DESCRIPTION = "\uD83D\uDE80 Realtime Congestion Based Location Recommendation Service - Vacation Project Server의 API 명세서입니다.";
    public static final String DEFINITION_VERSION = "v1";

    public static final String SECURITY_SCHEME_NAME = "bearer-key";
    public static final String SECURITY_SCHEME = "bearer";
    public static final String SECURITY_SCHEME_BEARER_FORMAT = "JWT";
    public static final String SECURITY_SCHEME_DESCRIPTION = "JWT 토큰 키를 입력해주세요";


    /**
     * Product
     */
    public static final String TAG_PRODUCT = "Product";
    public static final String TAG_PRODUCT_DESCRIPTION = "Product API";
}