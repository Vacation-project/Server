package Vacationproject.shoppingMall.domain.user.dto;

import Vacationproject.shoppingMall.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class UserDto {
    public record UserInfoResponse(String email, String nickname) {

        @Builder
        public UserInfoResponse{}

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
            String nickname){}


    public record NicknameResponse(boolean isAvailableNickname) {}
}
