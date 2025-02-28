package com.example.newsfeed.user.controller;

import com.example.newsfeed.enums.UserRole;
import com.example.newsfeed.user.dto.UserRequestDto;
import com.example.newsfeed.user.dto.UserResponseDto;
import com.example.newsfeed.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    // 관리자 회원가입
    @PostMapping
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {
        // role을 ADMIN으로 설정하여 관리자 회원가입 처리
        userRequestDto.setRole(UserRole.ADMIN);
        UserResponseDto responseDto = userService.signup(userRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
