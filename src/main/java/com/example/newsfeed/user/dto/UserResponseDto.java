package com.example.newsfeed.user.dto;

import com.example.newsfeed.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {
    private String name;         // 사용자 이름
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UserResponseDto(String name, UserRole role, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

