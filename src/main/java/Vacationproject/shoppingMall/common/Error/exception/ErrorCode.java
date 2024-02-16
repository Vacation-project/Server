package Vacationproject.shoppingMall.common.Error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {

    //TODO : ErrorCode 파일과 겹치는거 같습니다. ErrorCode or StatusCode 중 하나로 통합해야 될 거 같습니다.
    // -> ErrorCode에 통합.

    /**
     * Common
     */
    INTERNAL_SERVER_ERROR(500, "C004", "서버 오류", -1),
    INVALID_INPUT_VALUE(400, "C001", "잘못된 입력값입니다.", 9010),
    METHOD_NOT_ALLOWED(405, "C002", "메소드가 허용되지 않습니다.", 9020),
    ENTITY_NOT_FOUND(400, "C003", "존재하지 않는 엔티티입니다.", 9110),
    HANDLE_ACCESS_DENIED(403, "C006", "접근이 거부되었습니다.", 1000),
    INVALID_TYPE_VALUE(400, "C005", "잘못된 타입의 값입니다.", 9100),
    HTTP_CLIENT_ERROR(400, "C007", "HTTP 클라이언트 에러", 9030),

    /**
     * AWS S3 Errors
     */
    AWS_S3_UPLOAD_FAIL(400, "AWS001", "AWS S3 업로드 실패", 9040),
    AWS_S3_DELETE_FAIL(400, "AWS002", "AWS S3 삭제 실패", 9050),
    AWS_S3_FILE_SIZE_EXCEEDED(400, "AWS003", "파일 크기 초과", 9060),
    AWS_S3_FILE_TYPE_INVALID(400, "AWS004", "잘못된 파일 타입", 9070),

    /**
     * User
     */
    NOT_FOUND_USER(404, "U002", "사용자를 찾을 수 없습니다.", 2000),
    NICKNAME_DUPLICATION(409, "U001", "닉네임이 중복됩니다.", 2010),
    USER_MISMATCH(401, "U003", "사용자 정보가 일치하지 않습니다.", 1200),
    BANNED_USER(403, "U004", "사용자가 차단되었습니다.", 2020),
    BLACKLIST_BANNED_USER(403, "U005", "사용자가 블랙리스트에 등록되었습니다.", 2030),

    /**
     * Auth
     */
    UNAUTHORIZED_USER(401, "A001", "로그인이 필요합니다.", 1000),
    USER_ALREADY_EXISTS(400, "A002", "이미 가입된 사용자입니다.", 3010),
    NEED_TO_JOIN(400, "A003", "회원가입이 필요합니다.", 1040),
    LOGIN_SUCCESS(200, "A005", "로그인 성공", 1001),
    SIGNUP_COMPLETE(200, "A006", "회원가입 완료, 액세스 토큰이 발급되었습니다.", 1011),
    FILTER_ACCESS_DENIED(401, "A007", "접근이 거부되었습니다.", 1000),

    /**
     * Notification
     */
    SEND_FCM_PUSH_ERROR(400, "N001", "FCM 푸시 메시지 전송 실패", 4520),
    NOT_FOUND_NOTIFICATION(404, "N002", "알림을 찾을 수 없습니다.", 4530),

    // 일단 여기에 끊어 두었습니다. 아래부터 추가작업 하시면 될 것 같습니다.
    ;


    /**
     * Order
     */

    /**
     * OrderProduct
     */

    /**
     * Product
     */

    /**
     * Cart
     */

    /**
     * Review
     */

    /**
     * Favorite
     */

    /**
     * Category
     */

    //Login, Join

    //Member

    //Board

    //Comment

    private final int status;
    private final String code;
    private final String message;
    private final int legacyStatusCode; // StatusCode 에서 사용된 코드를 여기에 포함시키려고 함.

    ErrorCode(final int status, final String code, final String message, final int legacyStatusCode) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.legacyStatusCode = legacyStatusCode;
    }
    }