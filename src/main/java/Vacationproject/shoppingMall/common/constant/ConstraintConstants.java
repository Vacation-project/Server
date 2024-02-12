package Vacationproject.shoppingMall.common.constant;

public class ConstraintConstants {
    /**
     * Common
     */
    public static final String TIME_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String TIME_FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String TIME_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";


    /**
     * Product
     */
    public static final String PRODUCT_ID = "상품 ID";
    public static final String PRODUCT_NAME = "상품 이름";

    public static final String PRODUCT_PRICE = "상품 가격";

    public static final String PRODUCT_STOCK_QUANTITY = "상품 수량";

    public static final String PRODUCT_CONTENT = "상품 설명";

    public static final String PRODUCT_IMAGES = "상품 이미지들";

    public static final String PRODUCT_CATEGORY_ID = "상품 카테고리 ID";
    public static final String PRODUCT_REVIEWS = "상품 리뷰들";
    public static final String PRODUCT_RELATION_PRODUCTS = "연관 상품들";
    public static final String PRODUCT_CREATE_TIME = "상품 등록 시간";

    public static final int PRODUCT_PRICE_MIN = 100;
    public static final int PRODUCT_QUANTITY_MIN_SIZE = 1;
    public static final int PRODUCT_CONTENT_MIN_SIZE = 10;

    /**
     * Category
     */
    public static final String CATEGORY_ID = "카테고리 ID";
    public static final String CATEGORY_NAME = "카테고리 이름";

    /**
     * Review
     */
    public static final String REVIEW_USER_NICKNAME = "리뷰 회원 닉네임";
    public static final String REVIEW_TITLE = "리뷰 제목";
    public static final String REVIEW_COMMENT = "리뷰 내용";
    public static final String REVIEW_CREATE_DATE = "리뷰 작성 시간";
    public static final String REVIEW_RATING = "리뷰 별점";

    /**
     * OrderProduct
     */
    public static final String ORDER_PRODUCT_ID = "주문 상품 ID";
    public static final String ORDER_PRODUCT_QUANTITY = "주문 상품 갯주";
    public static final String ORDER_PRODUCT_ORDER_DATE = "주문 상품 주문 시간";
}