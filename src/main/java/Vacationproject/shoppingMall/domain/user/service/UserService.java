package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.dto.UserDto;
import Vacationproject.shoppingMall.domain.user.model.User;

public interface UserService {
    void registerUser(UserDto.RegisterRequest registerRequest);
    String loginUser(UserDto.LoginRequest loginRequest);
    boolean checkLoginId(String loginId);
    boolean checkNickname(String nickname);
    User getUserInfo(Long id);
    void updateUserInfo(Long id, UserDto.UpdateUserInfoRequest request);
}
