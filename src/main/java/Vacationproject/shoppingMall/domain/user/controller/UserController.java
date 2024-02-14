package Vacationproject.shoppingMall.domain.user.controller;

import Vacationproject.shoppingMall.domain.user.dto.UserDto;
import Vacationproject.shoppingMall.domain.user.model.User;
import Vacationproject.shoppingMall.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody UserDto.RegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
        return ResponseEntity.ok().body("회원가입 완료");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto.LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest);
        return ResponseEntity.ok().body("로그인 성공, 토큰: " + token);
    }

    @GetMapping("/users/checkLoginId")
    public ResponseEntity<?> checkLoginId(@RequestParam String loginId) {
        boolean isAvailable = userService.checkLoginId(loginId);
        return ResponseEntity.ok(isAvailable ? "사용 가능한 로그인 ID입니다." : "이미 사용 중인 로그인 ID입니다.");
    }

    @GetMapping("/users/checkNickname")
    public ResponseEntity<?> checkNickname(@RequestParam String nickname) {
        boolean isAvailable = userService.checkNickname(nickname);
        return ResponseEntity.ok(isAvailable ? "사용 가능한 닉네임입니다." : "이미 사용 중인 닉네임입니다.");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
        User user = userService.getUserInfo(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserInfo(@PathVariable Long id, @RequestBody UserDto.UpdateUserInfoRequest request) {
        userService.updateUserInfo(id, request);
        return ResponseEntity.ok().body("사용자 정보가 업데이트 되었습니다.");
    }
}
