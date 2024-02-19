/*
package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.dto.AuthDto;
import Vacationproject.shoppingMall.domain.user.model.SocialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService {

    @Value("${kakao.adminKey}")
    private String adminKey;

    private final RestTemplate restTemplate;
    private final SocialService socialService;

    @Override
    public AuthDto.OAuthSocialEmailResponse getKakaoId(String oauthAccessToken) {
        // Method implementation goes here
        String socialUrl = SocialType.KAKAO.getSocialUrl();
        HttpMethod httpMethod = SocialType.KAKAO.getHttpMethod();
        ResponseEntity<Map<String, Object>> response =
                socialService.validOauthAccessToken(oauthAccessToken, socialUrl, httpMethod);
        Map<String, Object> oauthBody = response.getBody();
        String id = String.valueOf(oauthBody.get("id"));
        log.info("id = {}", id);
        return AuthDto.OAuthSocialEmailResponse.to(id + "@KAKAO");
    }

    @Override
    public boolean revokeKakao(String socialUuid) {
        // Method implementation goes here
        String unlinkUrl = "https://kapi.kakao.com/v1/user/unlink";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("target_id_type", "user_id");
        params.add("target_id", socialUuid);
        log.info("socialUuid = {}", socialUuid);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + adminKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
        ResponseEntity<AuthDto.RevokeKakaoResponse> response = restTemplate.postForEntity(unlinkUrl, httpEntity, AuthDto.RevokeKakaoResponse.class);
        int revokeStatusCode = response.getStatusCode().value();
        return revokeStatusCode == 200;
    }
}
*/
