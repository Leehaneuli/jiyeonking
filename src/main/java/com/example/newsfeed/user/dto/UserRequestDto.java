package com.example.newsfeed.user.dto;

import com.example.newsfeed.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String name;         // 사용자 이름
    private String password;
    private UserRole role;

}
