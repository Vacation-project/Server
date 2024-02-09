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

    /*Product Operation*/
    public static final String CREATE_PRODUCT_SUMMARY = "상품 등록";
    public static final String CREATE_PRODUCT_DESCRIPTION = "상품 정보과 상품 이미지(images)를 이용하여 상품을 신규 등록합니다.";
    public static final String DETAIL_PRODUCT_SUMMARY = "ProductId와 일치하는 상품 정보를 가져옵니다.";
    public static final String DETAIL_PRODUCT_DESCRIPTION = "상품 생성";
    public static final String UPDATE_PRODUCT_FORM_SUMMARY = "상품 수정 폼";
    public static final String UPDATE_PRODUCT_FORM_DESCRIPTION = "ProductId와 일치하는 상품의 정보를 가져옵니다.";
    public static final String UPDATE_PRODUCT_SUMMARY = "상품 수정";
    public static final String UPDATE_PRODUCT_DESCRIPTION = "수정한 상품 정보와 상품 이미지(images)를 이용하여 상품 정보를 수정합니다.";
    public static final String DELETE_PRODUCT_SUMMARY = "상품 삭제";
    public static final String DELETE_PRODUCT_DESCRIPTION = "ProductId와 일치하는 상품을 삭제합니다.";
    public static final String CATEGORY_PRODUCT_SUMMARY = "카테고리별 상품 조회";
    public static final String CATEGORY_PRODUCT_DESCRIPTION = "CategoryId와 일치하는 카테고리의 상품을 조회합니다.";

    /*Product Parameter*/
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_ID_DESCRIPTION = "Product 의 ID";

    /**
     * Category
     */
    public static final String TAG_CATEGORY = "Category";
    public static final String TAG_CATEGORY_DESCRIPTION = "Category API";

    /*Category Operation*/

    /*Category Parameter*/
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_ID_DESCRIPTION = "Category 의 ID";
}
