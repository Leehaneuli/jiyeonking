package com.example.newsfeed.user.service;

import com.example.newsfeed.user.config.PasswordEncoder;
import com.example.newsfeed.user.dto.UserRequestDto;
import com.example.newsfeed.user.dto.UserResponseDto;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.enums.UserRole;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 처리
    @Transactional
    public UserResponseDto signup(UserRequestDto userRequestDto) {
        // role을 ADMIN으로 설정하여 관리자 회원가입 처리
        userRequestDto.setRole(UserRole.ADMIN);
        if (userRepository.existsByName(userRequestDto.getName())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = new User(userRequestDto.getName(), encodedPassword, userRequestDto.getRole());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getName(), savedUser.getRole(), savedUser.getCreatedAt(), savedUser.getModifiedAt());
    }

}
