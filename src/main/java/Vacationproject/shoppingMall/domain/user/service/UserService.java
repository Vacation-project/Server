package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.common.exception.StatusCode;
import Vacationproject.shoppingMall.domain.user.dto.AuthDto;
import Vacationproject.shoppingMall.domain.user.dto.UserDto;
import Vacationproject.shoppingMall.domain.user.exception.UserException;
import Vacationproject.shoppingMall.domain.user.model.SocialType;
import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final KakaoService kakaoService;
    private String currentUserSocialEmail;

    public User getCurrentUser() {
        return userRepository.findBySocialEmail(getCurrentUserSocialEmail()).orElseThrow(() -> new UserException(StatusCode.NOT_FOUND_USER));
    }

    public User getUserBySocialEmail(String socialEmail) {
        return userRepository.findBySocialEmail(socialEmail).orElseThrow(() -> new UserException(StatusCode.NOT_FOUND_USER));
    }

    public UserDto.UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(StatusCode.NOT_FOUND_USER));
        return new UserDto.UserInfoResponse(user.getEmail(),user.getNickname());
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException(StatusCode.NOT_FOUND_USER));
    }

    public UserDto.NicknameResponse isDuplicateNickname(String nickname) {
        validateDuplicateNickname(nickname);
        return new UserDto.NicknameResponse(true);
    }

    public void validateDuplicateNickname(String nickname) {
//        String pattern = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$";
        String pattern = "^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,16}$";
        if (!Pattern.matches(pattern, nickname)) {
            throw new UserException(StatusCode.NICKNAME_VALIDATE_ERROR);
        }
        if (userRepository.existsByNickname(nickname)) {
            throw new UserException(StatusCode.NICKNAME_DUPLICATION);
        }
    }

    @Transactional
    public UserDto.UserInfoResponse updateUserInfo(UserDto.UpdateUserInfoRequest updateUserInfoRequest, Long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new UserException(StatusCode.NOT_FOUND_USER));

        if (!currentUser.getNickname().equals(updateUserInfoRequest.nickname())) {
            validateDuplicateNickname(updateUserInfoRequest.nickname());
        }

        currentUser.updateUserInfo(updateUserInfoRequest);

        return UserDto.UserInfoResponse.of(currentUser);
    }

    /**
     *  만약 삭제기능을 추가한다면 해당 기능에서 삭제된 유저 구현해야함
     */
    /*@Transactional
    public AuthDto.AuthMessage revoke(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(StatusCode.NOT_FOUND_USER));

        SocialType socialType = user.getSocialType();
        String[] split = user.getSocialEmail().split("@");

        boolean result = switch (socialType) {
            case KAKAO -> kakaoService.revokeKakao(split[0]);
            default -> throw new UserException(StatusCode.SOCIAL_TYPE_ERROR);
        };

        if (result) {
            RevokeUser revokeUser = RevokeUser.builder()
                    .socialEmail(customEncryptUtil.hash(user.getSocialEmail()))
                    .revokedAt(LocalDateTime.now()).build();
            revokeUserRepository.save(revokeUser);
            redisService.deleteValues(user.getSocialEmail());
            *//**
             * 삭제할 곳 추가 구현
             *//*
            user.deletedUser();
        }
        return new AuthDto.AuthMessage(result, "Revoke result: " + result);
    }*/

    public static String getCurrentUserSocialEmail() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getName() == null) {
                throw new UserException(StatusCode.NOT_FOUND_USER);
            }
            return authentication.getName();
        }

    public void setCurrentUserSocialEmail(String currentUserSocialEmail) {
        this.currentUserSocialEmail = currentUserSocialEmail;
    }
}
