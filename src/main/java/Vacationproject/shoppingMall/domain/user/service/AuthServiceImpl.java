package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Auth 서비스 구현체
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public User register(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

        @Override
        public String login(String loginId, String password) {
            // 사용자를 찾고 비밀번호를 검증합니다.
            Optional<User> userOptional = userRepository.findByLoginId(loginId);
            if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
                // 성공적으로 로그인했을 경우, JWT 토큰을 생성하여 반환합니다. (여기서는 간단히 처리합니다.)
                // 실제로는 JWT 토큰 생성 로직을 구현해야 합니다.
                return "fake-jwt-token-for-" + loginId;
            }
            // 로그인 실패 처리
            throw new IllegalArgumentException("Login failed for user: " + loginId);
        }
}
