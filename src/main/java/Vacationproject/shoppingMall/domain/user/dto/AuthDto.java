package Vacationproject.shoppingMall.domain.user.dto;

import Vacationproject.shoppingMall.domain.user.model.Role;
import Vacationproject.shoppingMall.domain.user.model.SocialType;
import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.security.jwt.JwtDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jdk.jshell.spi.ExecutionControl;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Slf4j
public class AuthDto {


    public record SocialLoginRequest(
            @NotBlank
            @Schema(description = "소셜 타입", example = "KAKAO") // 스웨거 미사용으로 err.
            String socialType,
            @NotBlank
            @Schema(description = "Oauth2 Access 토큰", example = "ya29.a0Aa4xrXNXkiDBMm7MtSneVejzvup")
            String oauthAccessToken,
            @NotBlank
            String token
    ) {
    }

    public record LoginRequest(String socialEmail, String password) {
        @Builder
        public LoginRequest {
        }

        public static LoginRequest toLoginRequest(User user) {
            String socialEmail = user.getSocialEmail();
            return LoginRequest.builder()
                    .socialEmail(socialEmail)
                    .password(socialEmail + "buz")
                    .build();
        }

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(socialEmail, password);
        }
    }

    public record AdminLoginRequest(
            @Email
            String email,
            @NotEmpty
            String password) {
        public LoginRequest changeLoginRequest() {
            return LoginRequest.builder()
                    .socialEmail(email)
                    .password(password)
                    .build();
        }
    }

    public record SignUpRequest(
            @NotBlank
            String signToken,
            @NotBlank
            String nickname,
            @Email
            String email,
            @NotBlank
            String token
    ) {
        @Builder
        public SignUpRequest {
        }

        public SocialType getSocialType(String socialType) {
            if (socialType.equalsIgnoreCase(SocialType.KAKAO.name())) {
                return SocialType.KAKAO;
            } else throw new ExecutionControl.UserException(StatusCode.SOCIAL_TYPE_ERROR);
        }

        public User toUser(String socialEmail, String socialType, PasswordEncoder passwordEncoder) {
            return User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(socialEmail + "shop"))
                    .socialEmail(socialEmail)
                    .socialType(getSocialType(socialType))
                    .nickname(nickname)
                    .role(Role.USER)
                    .lastLoginDate(LocalDateTime.now())
                    .token(token).build();
        }

        public User toDummy(String email, String nickname, String socialEmail) {
            return User.builder()
                    .email(email)
                    .nickname(nickname)
                    .socialEmail(socialEmail)
                    .socialType(SocialType.KAKAO)
                    .role(Role.USER)
                    .lastLoginDate(LocalDateTime.now())
                    .build();
        }
    }

    public record SigningUser(String socialEmail, String socialUuid, String socialType) {
    }

    public record ReissueRequest(
            @Schema(example = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQ2") // example 확인해보기
            @NotBlank
            String refreshToken) {
    }


    public record AuthMessage(Object detailData, String detailMessage) {
    }

    public record SignAuthMessage(JwtDto detaildata, String detailMessage) {
    }

    public record SignToken(String signToken) {
    }

    public record RevokeKakaoResponse(String id) {
    }

    public record OAuthSocialEmailResponse(String socialEmail) {

        public static OAuthSocialEmailResponse to(String socialEmail) {
            return new OAuthSocialEmailResponse(socialEmail);
        }
    }

    public record LogoutResponse(
            @Schema(description = "로그아웃 확인", example = "true")
            boolean isLogout) {
        public static LogoutResponse of(boolean isLogout) {
            return new LogoutResponse(!isLogout);
        }
    }

}
