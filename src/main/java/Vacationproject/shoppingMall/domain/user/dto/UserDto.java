package Vacationproject.shoppingMall.domain.user.dto;

import Vacationproject.shoppingMall.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {
    public record UserInfoResponse(String email, String nickname) {
        @Builder
        public UserInfoResponse {
        }

        public static UserInfoResponse of(User user) {
            return UserInfoResponse.builder()
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .build();
        }
    }

    public record UpdateUserInfoRequest(
            @NotBlank
            String email,
            @NotBlank
            String nickname,
            String state,
            String city,
            String town
    ) {
        public String getNickname() {
            return nickname;
        }

        public String getState() {
            return state;
        }

        public String getCity() {
            return city;
        }

        public String getTown() {
            return town;
        }
       /*
       record에 직접 getter 메서드를 정의하는 경우, 필드 이름과 같은 이름의 메서드를 사용하면, 메서드가 자기 자신을 무한히 호출하는 문제가 발생합니다.
       따라서 위와 같이 수정.
       public String getNickname() {
            return getNickname();
        }

        public String getState() {
            return getState();
        }

        public String getCity() {
            return getCity();
        }

        public String getTown() {
            return getTown();
        }*/
    }
    /*
    UserDto의 UpdateUserInfoRequest 클래스를 Java의 record로 했었는데, record는 불변의 데이터를 표현하기 위한 간결한 방법을 제공함.
     record는 자동으로 모든 필드에 대한 getter 메서드를 생성.
     그러나 여기서는 getState, getCity, getTown 메서드를 Object 타입으로 반환하는 대신, 실제 필드 타입으로 정의하는 것이 더 적절해 보임
    . @NotBlank 어노테이션은 String 필드에 대해 사용되므로, state, city, town도 String 타입으로 처리하는 것이 맞기떄문에 수정.
     */
   /* @Getter
    public static class UpdateUserInfoRequest {
            private String nickname; // 필드 이름 주의
            private String state;
            private String city;
            private String town;

        public String nickname() {

        }
    }*/
    /*public record UpdateUserInfoRequest(
            @NotBlank String email,
            @NotBlank String nickname,
            String state, // `Object` 대신 `String` 타입 사용
            String city,
            String town) {
    }*/


    public record NicknameResponse(boolean isAvailableNickname) {
    }

    // 사용자 등록 요청을 위한 내부 클래스
    @Getter
    public static class RegisterRequest {
        private String loginId;
        private String password;
        private String nickName;
        private String gender;
        private String state;
        private String city;
        private String town;


    }

    // 사용자 로그인 요청을 위한 내부 클래스
    @Getter
    public static class LoginRequest {
        private String loginId;
        private String password;

    }

    // 새로운 내부 클래스 추가
    @Getter
    public static class Response {
        private Long id;
        private String loginId;
        private String nickname;

    }
}
