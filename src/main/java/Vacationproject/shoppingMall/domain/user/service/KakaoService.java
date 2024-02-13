package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 키카오 인증에 필요한 서비스 로직을 구현
 */
public interface KakaoService {

    AuthDto.OAuthSocialEmailResponse getKakaoId(String oauthAccessToken);

    boolean revokeKakao(String socialUuid);

}