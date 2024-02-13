package Vacationproject.shoppingMall.domain.user.controller;

import Vacationproject.shoppingMall.common.dto.ApiResponse;
import Vacationproject.shoppingMall.domain.user.dto.AuthDto;
import Vacationproject.shoppingMall.domain.user.security.jwt.JwtDto;
import Vacationproject.shoppingMall.domain.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static Vacationproject.shoppingMall.common.dto.ApiResponse.success;

/**
 * 로그인 및 회원가입 요청 처리
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
// @Tag(name = TAG_AUTH, description = TAG_AUTH_DESCRIPTION)
@Slf4j
public class AuthController {

    private final AuthService authService;

       @Operation(summary = AUTH_LOGIN, description = AUTH_LOGIN_DESCRIPTION) // 스웨거 3
       @PostMapping("/login")
       public ApiResponse<?> login(@RequestBody @Valid AuthDto.SocialLoginRequest socialLoginRequest) {
           AuthDto.AuthMessage authMessage = authService.loginAccess(socialLoginRequest);
           return success(authMessage.detailData());
       }

       @Operation(summary = AUTH_SIGNUP)
       @PostMapping("/signup")
       public ApiResponse<JwtDto> signup(@Valid @RequestBody AuthDto.SignUpRequest signUpRequest) {
           AuthDto.SignAuthMessage signAuthMessage = authService.signUp(signUpRequest);
           return success(signAuthMessage.detaildata());
       }

       @Operation(summary = AUTH_REISSUE)
       @PostMapping("/reissue")
       public ApiResponse<JwtDto> reissue(@RequestBody AuthDto.ReissueRequest reissueRequest) {
           return success(authService.reissue(reissueRequest));
       }

       @Operation(summary = AUTH_LOGOUT)
       @SecurityRequirement(name = SwaggerConstants.SECURITY_SCHEME_NAME)
       @PostMapping("/logout")
       public ApiResponse<AuthDto.LogoutResponse> logout(@AuthenticationPrincipal PrincipalDetails principalDetails) {
           return success(authService.logout(principalDetails.getUserId()));
       }

       @PostMapping("/login/admin")
       @Operation(summary = ADMIN_LOGIN, description = ADMIN_LOGIN_DESCRIPTION)
       public ApiResponse<?> adminLogin(@RequestBody @Valid AdminLoginRequest adminLoginRequest) {
           AuthDto.AuthMessage authMessage = authService.loginAdmin(adminLoginRequest);
           return success(authMessage.detailData());
       }

}
