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
     * Common
     */
    public static final String PAGING = "pageable";
    public static final String PAGING_DESCRIPTION = "";

    public static final String SORT_KEY = "sortKey";
    public static final String SORT_KEY_DESCRIPTION = "최신순(기본값): createdAt, 인기순: popular, 고가순: highPrice, 저가순: lowestPrice";

    /**
     * Home
     */
    public static final String TAG_HOME = "Home";
    public static final String TAG_HOME_DESCRIPTION = "Home API";
    public static final String HOME_SUMMARY = "메인 페이지";
    public static final String HOME_DESCRIPTION = "메인 페이지 정보를 가져옵니다.";


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

    public static final String SEARCH_PRODUCT_SUMMARY = "상품 검색";
    public static final String SEARCH_PRODUCT_DESCRIPTION = "검색 결과에 일치하는 상품을 조회합니다.";

    /*Product Parameter*/
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_ID_DESCRIPTION = "Product 의 ID";

    /**
     * Category
     */
    public static final String TAG_CATEGORY = "Category";
    public static final String TAG_CATEGORY_DESCRIPTION = "Category API";
    public static final String CREATE_CATEGORY_SUMMARY = "카테고리 생성";
    public static final String CREATE_CATEGORY_DESCRIPTION = "카테고리를 생성합니다.";
    public static final String FIND_CATEGORY_SUMMARY = "카테고리 조회";
    public static final String FIND_CATEGORY_DESCRIPTION = "카테고리를 조회합니다.";

    /*Category Operation*/

    /*Category Parameter*/
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_ID_DESCRIPTION = "Category 의 ID";

    /**
     * Review
     */
    public static final String TAG_REVIEW = "Review";
    public static final String TAG_REVIEW_DESCRIPTION = "Review API";

    /*Review Operation*/
    public static final String WRITE_REVIEW_FORM_SUMMARY = "리뷰 작성 폼";
    public static final String WRITE_REVIEW_FORM_DESCRIPTION = "주문 상품의 리뷰를 작성하는 폼을 가져옵니다.";
    public static final String WRITE_REVIEW_SUMMARY = "리뷰 작성";
    public static final String WRITE_REVIEW_DESCRIPTION = "주문 상품의 리뷰를 작성합니다.";
    public static final String NOT_WRITTEN_REVIEW_SUMMARY = "작성하지 않은 주문 상품 목록 조회";
    public static final String NOT_WRITTEN_REVIEW_DESCRIPTION = "회원이 작성하지 않은 주문 상품 목록을 조회합니다.";
    public static final String WRITTEN_REVIEW_SUMMARY = "작성한 리뷰 목록 조회";
    public static final String WRITTEN_REVIEW_DESCRIPTION = "회원이 작성한 리뷰 목록을 조회합니다.";
    public static final String UPDATE_REVIEW_FROM_SUMMARY = "리뷰 수정 폼";
    public static final String UPDATE_REVIEW_FROM_DESCRIPTION = "수정할 리뷰 수정 폼을 가져옵니다.";
    public static final String UPDATE_REVIEW_SUMMARY = "리뷰 수정";
    public static final String UPDATE_REVIEW_DESCRIPTION = "리뷰를 수정합니다.";
    public static final String DELETE_REVIEW_SUMMARY = "리뷰 수정";
    public static final String DELETE_REVIEW_DESCRIPTION = "리뷰를 수정합니다.";

    /*Review Parameter*/
    public static final String REVIEW_ID = "reviewId";

    /**
     * Favorite
     */
    public static final String TAG_FAVORITE = "Favorite";
    public static final String TAG_FAVORITE_DESCRIPTION = "Favorite API";

    /**
     * OrderProduct
     */

    /*OrderProduct Operation*/

    /*OrderProduct Parameter*/
    public static final String ORDER_PRODUCT_Id = "orderProductId";

}
