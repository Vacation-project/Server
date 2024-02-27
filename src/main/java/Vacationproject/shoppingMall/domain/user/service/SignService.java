package Vacationproject.shoppingMall.domain.user.service;

import Vacationproject.shoppingMall.domain.user.dto.SignRequest;
import Vacationproject.shoppingMall.domain.user.dto.SignResponse;
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

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final UserRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SignResponse login(SignRequest request) throws Exception {
        User member = memberRepository.findByLoginId(request.getLoginId()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        return SignResponse.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .name(member.getName())
                .email(member.getEmail())
                .nickName(member.getNickName())
                .gender(member.getGender())
                .roles(member.getRoles())
                .address(member.getAddress())
                .token(jwtProvider.createToken(member.getLoginId(), member.getRoles()))
                .build();

    }

    public boolean register(SignRequest request) throws Exception {
        try {
            User member = User.builder()
                    .loginId(request.getLoginId())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .nickName(request.getNickName())
                    .email(request.getEmail())
                    .gender(request.getGender())
                    .address(request.getAddress())
                    .build();

            member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            memberRepository.save(member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    public SignResponse getMember(String userId) throws Exception {
        User member = memberRepository.findByLoginId(userId)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return new SignResponse(member);
    }

}
