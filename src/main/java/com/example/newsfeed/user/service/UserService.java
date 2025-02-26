package com.example.newsfeed.user.service;

import com.example.newsfeed.user.dto.UserRequestDto;
import com.example.newsfeed.user.dto.UserResponseDto;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import com.example.newsfeed.user.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 처리
    @Transactional
    public UserResponseDto signup(UserRequestDto userRequestDto) {
        if (userRepository.existsByName(userRequestDto.getName())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = new User(userRequestDto.getName(), encodedPassword, userRequestDto.getRole());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getName(), savedUser.getRole(), savedUser.getCreatedAt(), savedUser.getModifiedAt());
    }

    // 로그인 처리
    public boolean login(UserRequestDto userRequestDto) {
        User user = userRepository.findByName(userRequestDto.getName()).orElse(null);
        return user != null && passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword());
    }
}
