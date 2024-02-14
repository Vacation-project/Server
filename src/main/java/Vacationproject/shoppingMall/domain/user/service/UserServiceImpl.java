package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.dto.UserDto;
import Vacationproject.shoppingMall.domain.user.model.Address;
import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.repository.UserRepository;
import Vacationproject.shoppingMall.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void registerUser(UserDto.RegisterRequest registerRequest) {
        if (userRepository.existsByLoginId(registerRequest.getLoginId())) {
            throw new IllegalArgumentException("이미 사용 중인 로그인 ID입니다.");
        }
        User newUser = User.builder()
                .loginId(registerRequest.getLoginId())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .nickname(registerRequest.getNickName()) // 필드 이름이 일치하는지 확인
                .gender(registerRequest.getGender())
                .address(new Address(registerRequest.getState(), registerRequest.getCity(), registerRequest.getTown()))
                .build();

        userRepository.save(newUser);
    }

    @Override
    public String loginUser(UserDto.LoginRequest loginRequest) {
        // 로그인 로직 구현 (JWT 토큰 반환 등)
        // 여기서는 예시로 간단한 문자열 반환
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
        user.setNickname(request.getNickname()); // 수정: 메서드 이름 변경 getNickName() -> getNickname()
        user.setAddress(new Address(request.getState(), request.getCity(), request.getTown()));
        userRepository.save(user);
    }
}

