package Vacationproject.shoppingMall.domain.user.security.oauth2.user;

import Vacationproject.shoppingMall.domain.user.security.oauth2.exception.OAuth2AuthenticationProcessingException;
import Vacationproject.shoppingMall.domain.user.security.oauth2.user.google.GoogleOAuth2UserInfo;
import Vacationproject.shoppingMall.domain.user.security.oauth2.user.kakao.KakaoOAuth2UserInfo;
import Vacationproject.shoppingMall.domain.user.security.oauth2.user.naver.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId,
                                                   String accessToken,
                                                   Map<String, Object> attributes) {
        if (OAuth2Provider.GOOGLE.getRegistrationId().equals(registrationId)) {
            return new GoogleOAuth2UserInfo(accessToken, attributes);
        } else if (OAuth2Provider.NAVER.getRegistrationId().equals(registrationId)) {
            return new NaverOAuth2UserInfo(accessToken, attributes);
        } else if (OAuth2Provider.KAKAO.getRegistrationId().equals(registrationId)) {
            return new KakaoOAuth2UserInfo(accessToken, attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + " is not supported");
        }
    }
}
