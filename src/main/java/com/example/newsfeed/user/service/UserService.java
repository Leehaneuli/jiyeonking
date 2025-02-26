package com.example.newsfeed.user.service;

import com.example.newsfeed.user.dto.UserRequestDto;
import com.example.newsfeed.user.dto.UserResponseDto;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import com.example.newsfeed.config.PasswordEncoder;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;

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
    public UserResponseDto login(UserRequestDto loginRequestDto) {
        // 사용자 이름으로 조회
        User user = userRepository.findByName(loginRequestDto.getName())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        // 비밀번호 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호입니다.");
        }

        // 세션에 사용자 정보 저장
        session.setAttribute("SESSION_KEY", user.getName());
        return new UserResponseDto(user.getName(), user.getRole(), user.getCreatedAt(), user.getModifiedAt());
    }

    // 비밀번호 변경
    public void changePassword(String userId, String oldPassword, String newPassword) {
        User user = userRepository.findByName(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // 사용자 상세 조회
    public UserResponseDto getUserById(String userId) {
        User user = userRepository.findByName(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return new UserResponseDto(
                user.getName(),  // 사용자 이름
                user.getRole(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(String userId, String password) {
        User user = userRepository.findByName(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);
    }







}
