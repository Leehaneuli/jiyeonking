package com.example.newsfeed.friend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FriendRequestDto {

    @NotNull(message = "userId는 필수 항목입니다.")
    private Long userId;
}
