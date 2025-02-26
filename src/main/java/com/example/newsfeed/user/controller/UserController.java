package com.example.newsfeed.user.controller;

import com.example.newsfeed.user.dto.UserRequestDto;
import com.example.newsfeed.user.dto.UserResponseDto;
import com.example.newsfeed.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 일반 사용자 회원가입
    @PostMapping
    public ResponseEntity<?> signup(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto responseDto = userService.signup(userRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 로그인 내일..
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserRequestDto userRequestDto) {
        boolean loginSuccess = userService.login(userRequestDto);
        return ResponseEntity.ok("로그인 성공!");
    }
}
