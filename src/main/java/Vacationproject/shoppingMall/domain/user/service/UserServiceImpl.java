package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.dto.UserDto;
import Vacationproject.shoppingMall.domain.user.model.Address;
import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(UserDto.RegisterRequest registerRequest) {
        if (userRepository.existsByLoginId(registerRequest.loginId())) {
            throw new IllegalArgumentException("이미 사용 중인 로그인 ID입니다.");
        }
        User newUser = User.builder()
                .loginId(registerRequest.loginId())
                .password(passwordEncoder.encode(registerRequest.password()))
                .nickname(registerRequest.nickName())
                .gender(registerRequest.gender())
                .address(new Address(registerRequest.state(), registerRequest.city(), registerRequest.town()))
                .build();

        userRepository.save(newUser);
    }

    @Override
    public String loginUser(UserDto.LoginRequest loginRequest) {
        // 로그인 로직 구현 (JWT 토큰 반환 등)
        return "token_example";
    }

    @Override
    public boolean checkLoginId(String loginId) {
        return !userRepository.existsByLoginId(loginId);
    }

    @Override
    public boolean checkNickname(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    @Override
    public User getUserInfo(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public void updateUserInfo(Long id, UserDto.UpdateUserInfoRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Address userAddress = new Address(request.state(), request.city(), request.town());
        user.update(request.nickname(), userAddress);
    }
}
