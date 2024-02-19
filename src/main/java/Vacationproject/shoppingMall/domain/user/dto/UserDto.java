package Vacationproject.shoppingMall.domain.user.dto;

import Vacationproject.shoppingMall.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;

public class UserDto {

    public record UserInfoResponse(String email, String nickname) {
        public static UserInfoResponse of(User user) {
            return new UserInfoResponse(user.getEmail(), user.getNickname());
        }
    }

    public record UpdateUserInfoRequest(
        @NotBlank String email,
        @NotBlank String nickname,
        String state,
        String city,
        String town
    ) {}

    public record NicknameResponse(boolean isAvailableNickname) {}

    public record RegisterRequest(
        String loginId,
        String password,
        String nickName,
        String gender,
        String state,
        String city,
        String town
    ) {}

    public record LoginRequest(String loginId, String password) {}

    public record Response(Long id, String loginId, String nickname) {}
}
