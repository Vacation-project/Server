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
    public ApiResponse<String> registerUser(@RequestBody UserDto.RegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
        return ApiResponse.success("회원가입 완료", null); // 데이터가 없는 경우 null 전달
    }

    @PostMapping("/auth/login")
    public ApiResponse<String> loginUser(@RequestBody UserDto.LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest);
        return ApiResponse.success("로그인 성공, 토큰: " + token, token);
    }

    @GetMapping("/users/checkLoginId")
    public ApiResponse<Boolean> checkLoginId(@RequestParam String loginId) {
        boolean isAvailable = userService.checkLoginId(loginId);
        String message = isAvailable ? "사용 가능한 로그인 ID입니다." : "이미 사용 중인 로그인 ID입니다.";
        return ApiResponse.success(message, isAvailable);
    }

    @GetMapping("/users/checkNickname")
    public ApiResponse<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isAvailable = userService.checkNickname(nickname);
        String message = isAvailable ? "사용 가능한 닉네임입니다." : "이미 사용 중인 닉네임입니다.";
        return ApiResponse.success(message, isAvailable);
    }

    @GetMapping("/users/{id}")
    public ApiResponse<UserDto.Response> getUserInfo(@PathVariable Long id) {
        User user = userService.getUserInfo(id);
        UserDto.Response response = new UserDto.Response(); // 실제로는 User 엔티티에서 DTO로 변환하는 로직 필요
        return ApiResponse.success("사용자 정보 조회 성공", response);
    }

    @PutMapping("/users/{id}")
    public ApiResponse<String> updateUserInfo(@PathVariable Long id, @RequestBody UserDto.UpdateUserInfoRequest request) {
        userService.updateUserInfo(id, request);
        return ApiResponse.success("사용자 정보가 업데이트 되었습니다.", null); // 데이터가 없는 경우 null 전달
    }
}