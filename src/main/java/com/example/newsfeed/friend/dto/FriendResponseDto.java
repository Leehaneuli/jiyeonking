package com.example.newsfeed.friend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FriendResponseDto {

    private final Long id;
    private final Long followerId;
    private final Long followeeId;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
}