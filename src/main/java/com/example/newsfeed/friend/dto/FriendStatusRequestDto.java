package com.example.newsfeed.friend.dto;

import com.example.newsfeed.enums.FriendStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FriendStatusRequestDto {

    @NotNull(message = "APPROVED 또는 REJECTED만 허용합니다.")
    private FriendStatus status;
}