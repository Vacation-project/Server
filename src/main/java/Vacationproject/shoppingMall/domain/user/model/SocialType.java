package Vacationproject.shoppingMall.domain.user.model;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum SocialType {
    KAKAO(
            "kakao", // 카카오 소셜 미디어의 이름을 문자열로 저장
            "https://kapi.kakao.com/v2/user/me", // 카카오 API에서 사용자 정보를 조회할 때 사용할 URL
            // TODO: api 가져오기
            HttpMethod.GET
    );

    private String socialName;
    private String socialUrl;
    private HttpMethod httpMethod;

    SocialType(String socialName, String socialUrl, HttpMethod httpMethod) {
        this.socialName = socialName;
        this.socialUrl = socialUrl;
        this.httpMethod = httpMethod;
    }
}
