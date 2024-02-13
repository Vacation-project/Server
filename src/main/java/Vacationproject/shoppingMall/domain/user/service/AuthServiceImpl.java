package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.dto.AuthDto;
import Vacationproject.shoppingMall.domain.user.exception.AuthException;
import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.repository.UserRepository;
import Vacationproject.shoppingMall.domain.user.security.jwt.JwtDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Auth 서비스 구현체
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    //private final JwtProvider jwtProvider;
    //private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final UserService userService;
    private final KakaoService kakaoService;

    @Override
    @Transactional(noRollbackFor = {AuthException.class})
    public AuthDto.AuthMessage loginAccess(AuthDto.SocialLoginRequest socialLoginRequest) {
        OAuthSocialEmailResponse response = fetchSocialEmail(socialLoginRequest);
        String socialEmail = response.socialEmail();

        checkIsSleepingUser(socialEmail);
        Optional<User> findUser = userRepository.findBySocialEmail(socialEmail);
        checkIsRevokeUser(socialEmail);
        checkIsBlackListAndRevokeUser(socialEmail);
        AuthMessage loginMessage;
        if (findUser.isPresent()) {
            User user = findUser.get();
            checkUserStatus(user);
            user.updateLastLoginDate();
            user.updateFcmToken(socialLoginRequest.fcmToken());

            JwtDto jwtDto = login(LoginRequest.toLoginRequest(user));
            loginMessage = new AuthMessage(
                    jwtDto,
                    StatusCode.LOGIN.getMessage()
            );
        } else {
            String signToken = jwtProvider.createSignToken(socialEmail);
            SignToken signTokenResponse = new SignToken(signToken);
            log.info("signTokenResponse={}", signTokenResponse.signToken());
            throw new AuthException(StatusCode.NEED_TO_SIGNUP, signTokenResponse);
        }
        return loginMessage;
    }

    @Override
    public JwtDto login(AuthDto.LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        return jwtProvider.issue(user);
    }

    @Override
    public JwtDto adminJwt(AuthDto.LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        return jwtProvider.adminIssue(user);
    }

    @Override
    @Transactional
    public AuthDto.SignAuthMessage signUp(AuthDto.SignUpRequest signUpRequest) {
        String signToken = jwtProvider.resolveSignToken(signUpRequest.signToken());
        if (!jwtProvider.validate(signToken))
            throw new AuthException(StatusCode.SIGNUP_TOKEN_ERROR);

        SigningUser signKey = jwtProvider.getSignKey(signToken);
        String socialEmail = signKey.socialEmail();
        String socialType = signKey.socialType();
        String nickname = signUpRequest.nickname();

        checkDuplicationNickName(nickname);
        User user = signUpRequest.toUser(socialEmail, socialType, passwordEncoder);
        userRepository.save(user);

        user.updateFcmToken(signUpRequest.fcmToken());
        JwtDto jwtDto = login(LoginRequest.toLoginRequest(user));
        return new SignAuthMessage(
                jwtDto,
                StatusCode.SIGNUP_COMPLETE.getMessage()
        );
    }


    @Override
    @Transactional
    public AuthDto.LogoutResponse logout(Long userId) {
        User user = userService.getUser(userId);

        user.removeFcmToken();
        redisService.deleteValues(user.getSocialEmail());
        String values = redisService.getValues(user.getSocialEmail());
        boolean exist = checkIsExistRefresh(values);

        return LogoutResponse.of(exist);
    }

    @Override
    @Transactional
    public AuthDto.AuthMessage loginAdmin(AuthDto.AdminLoginRequest adminLoginRequest) {
        String email = adminLoginRequest.email();
        User admin = userRepository.findBySocialEmail(email).orElseThrow(() -> new UserException(StatusCode.NOT_FOUND_USER));

        if (!admin.getRole().equals(Role.ROLE_ADMIN)) throw new UserException(StatusCode.ROLE_ACCESS_ERROR);
        JwtDto jwtDto = adminJwt(adminLoginRequest.changeLoginRequest());
        return new AuthMessage(jwtDto, StatusCode.LOGIN.getMessage());
    }

    private boolean checkIsExistRefresh(String values) {
        return !Objects.isNull(values);
    }
}
