package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.model.User;

/**
 * 사용자 인증에 필요한 서비스 로직을 구현
 */
public interface AuthService {
    User register(User user);
    String login(String loginId, String password);

}

