package Vacationproject.shoppingMall.common.Error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {

    /**
     * Common
     */
    INVALID_INPUT_VALUE(400, "C001", "잘못된 입력값입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "잘못된 입력값입니다."),
    ENTITY_NOT_FOUND(400, "C003", "존재하지 않는 엔티티입니다."),
    INTERNAL_SERVER_ERROR(500, "C004", "서버 오류"),
    INVALID_TYPE_VALUE(400, "C005", " 잘못된 타입입니다."),
    HANDLE_ACCESS_DENIED(403, "C006", "접근이 금지됐습니다."),

    /**
     * 회원가입, 로그인
     */
    UNAUTHORIZED_USER(401, "A001", "로그인이 필요합니다."),
    USER_ALREADY_EXISTS(400, "A002", "이미 가입된 유저입니다."),
    NEED_TO_JOIN(400, "A003", "회원가입이 필요합니다."),
    USER_MISMATCH(401, "A004", "회원 정보가 불일치합니다."),

    /**
     * User
     */
    NICKNAME_DUPLICATION(409, "U001", "중복되는 닉네임입니다."),
    USER_NOT_FOUND(404, "U002", "존재하지 않는 회원입니다."),
    INVALID_INPUT_ID_PASSWORD(400, "U003", "Id 또는 Password가 일치하지 않습니다."),

    /**
     * Order
     */

    /**
     * OrderProduct
     */
    ORDER_PRODUCT_NOT_FOUND(400, "OP001", "존재하지 않는 주문 상품입니다."),


    /**
     * Product
     */
    PRODUCT_NAME_DUPLICATION(409, "P001", "이미 존재하는 상품 이름입니다."),
    PRODUCT_NOT_FOUND(400, "P002", "존재하지 않는 상품입니다."),

    /**
     * Cart
     */

    /**
     * Review
     */
    REVIEW_ALREADY_EXISTS(400, "R001", "이미 리뷰가 작성됐습니다."),

    /**
     * Favorite
     */

    /**
     * Category
     */
    CATEGORY_NAME_DUPLICATION(409, "CT001", "이미 존재하는 카테고리 이름입니다."),
    CATEGORY_NOT_FOUND(400, "CT002", "존재하지 않는 카테고리입니다.");



    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}