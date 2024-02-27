/*
package Vacationproject.shoppingMall.domain.user.service;


import Vacationproject.shoppingMall.domain.user.dto.UserDto;
import Vacationproject.shoppingMall.domain.user.dto.UserRegistrationDto;
import Vacationproject.shoppingMall.domain.user.model.Authority;
import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.repository.UserRepository;
import Vacationproject.shoppingMall.domain.user.security.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 회원가입
    public UserDto register(UserRegistrationDto registrationDto) {
        if (userRepository.existsByLoginId(registrationDto.loginId())) {
            throw new IllegalArgumentException("이미 사용중인 로그인 ID입니다.");
        }

        // 비밀번호 암호화 및 사용자 생성
        User newUser = User.builder()
                .loginId(registrationDto.loginId())
                .password(passwordEncoder.encode(registrationDto.password()))
                .nickName(registrationDto.nickName())
                .name(registrationDto.name())
                .gender(registrationDto.gender())
                .email(registrationDto.email())
                .address(registrationDto.address())
                .build();

        newUser.setRoles(Collections.singletonList(new Authority("ROLE_USER"))); // 기본 권한 설정
        userRepository.save(newUser);

        // 토큰 생성
        String token = jwtProvider.createToken(newUser.getLoginId(), newUser.getRoles().stream().map(Authority::getName).collect(Collectors.toList()));

        return new UserDto(
                newUser.getId(),
                newUser.getLoginId(),
                newUser.getNickName(),
                newUser.getName(),
                newUser.getGender(),
                newUser.getEmail(),
                newUser.getAddress(),
                newUser.getRoles(),
                token
        );
    }

    // 로그인
    public UserDto login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(() ->
                new BadCredentialsException("잘못된 로그인 정보입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("잘못된 로그인 정보입니다.");
        }

        String token = jwtProvider.createToken(user.getLoginId(), user.getRoles().stream().map(Authority::getName).collect(Collectors.toList()));

        return new UserDto(
                user.getId(),
                user.getLoginId(),
                user.getNickName(),
                user.getName(),
                user.getGender(),
                user.getEmail(),
                user.getAddress(),
                user.getRoles(),
                token
        );
    }

    // 사용자 정보 조회
    public UserDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return new UserDto(
                user.getId(),
                user.getLoginId(),
                user.getNickName(),
                user.getName(),
                user.getGender(),
                user.getEmail(),
                user.getAddress(),
                user.getRoles(),
                null // 토큰은 조회에서 제외
        );
    }

}
*/
