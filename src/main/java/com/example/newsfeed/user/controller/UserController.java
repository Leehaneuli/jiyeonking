package com.example.newsfeed.user.controller;

import com.example.newsfeed.user.dto.ChangePasswordRequestDto;
import com.example.newsfeed.user.dto.UserRequestDto;
import com.example.newsfeed.user.dto.UserResponseDto;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import com.example.newsfeed.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 일반 사용자 회원가입
    @PostMapping
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto responseDto = userService.signup(userRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto loginRequestDto) {
        UserResponseDto responseDto = userService.login(loginRequestDto);
        return ResponseEntity.ok("로그인 성공! ");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // 세션 무효화 (로그아웃)
        }
        return ResponseEntity.ok("로그아웃 성공!");
    }

    // 비밀번호 변경
    @PatchMapping("/{userId}/password")
    public ResponseEntity<String> changePassword(@PathVariable String userId, @RequestBody ChangePasswordRequestDto requestDto) {
        userService.changePassword(userId, requestDto.getOldPassword(), requestDto.getNewPassword());
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }

    // 사용자 상세 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String userId) {
        UserResponseDto userResponseDto = userService.getUserById(userId);
        return ResponseEntity.ok(userResponseDto);
    }

    // 회원탈퇴
    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<String> deleteUser(
            @PathVariable String userId,
            @RequestParam String password) {
        userService.deleteUser(userId, password);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }

}
