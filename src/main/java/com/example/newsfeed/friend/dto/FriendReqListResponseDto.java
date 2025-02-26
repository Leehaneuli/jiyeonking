package com.example.newsfeed.friend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FriendReqListResponseDto {

    private final Long id;
    private final Long userId;
    private final String name;
    private final String status;
    private final LocalDateTime createdAt;
}