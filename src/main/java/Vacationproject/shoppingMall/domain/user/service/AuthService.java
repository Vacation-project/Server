/*
package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.dto.*;
import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.security.jwt.JwtDto;

*/
/**
 * 사용자 인증에 필요한 서비스 로직을 구현
 *//*

public interface AuthService {
    */
/*User register(User user);
    String login(String loginId, String password);*//*

    AuthDto.AuthMessage loginAccess(AuthDto.SocialLoginRequest socialLoginRequest);

    JwtDto login(AuthDto.LoginRequest loginRequest);
    JwtDto adminJwt(AuthDto.LoginRequest loginRequest);

    AuthDto.SignAuthMessage signUp(AuthDto.SignUpRequest signUpRequest);


    AuthDto.LogoutResponse logout(Long userId);

    AuthDto.AuthMessage loginAdmin(AuthDto.AdminLoginRequest adminLoginRequest);
}

*/
