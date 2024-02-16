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
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody UserDto.RegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
        return ResponseEntity.ok(ApiResponse.success("회원가입 완료"));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestBody UserDto.LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("로그인 성공, 토큰: ", token));
    }

    @GetMapping("/users/checkLoginId")
    public ResponseEntity<ApiResponse<Boolean>> checkLoginId(@RequestParam String loginId) {
        boolean isAvailable = userService.checkLoginId(loginId);
        String message = isAvailable ? "사용 가능한 로그인 ID입니다." : "이미 사용 중인 로그인 ID입니다.";
        return ResponseEntity.ok(ApiResponse.success(message, isAvailable));
    }

    @GetMapping("/users/checkNickname")
    public ResponseEntity<ApiResponse<Boolean>> checkNickname(@RequestParam String nickname) {
        boolean isAvailable = userService.checkNickname(nickname);
        String message = isAvailable ? "사용 가능한 닉네임입니다." : "이미 사용 중인 닉네임입니다.";
        return ResponseEntity.ok(ApiResponse.success(message, isAvailable));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> getUserInfo(@PathVariable Long id) {
        User user = userService.getUserInfo(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse<String>> updateUserInfo(@PathVariable Long id, @RequestBody UserDto.UpdateUserInfoRequest request) {
        userService.updateUserInfo(id, request);
        return ResponseEntity.ok(ApiResponse.success("사용자 정보가 업데이트 되었습니다."));
    }
}
