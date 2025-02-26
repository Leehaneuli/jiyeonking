package com.example.newsfeed.friend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendListResponseDto {

    private final Long followerId;
    private final String followerName;
    private final Long followeeId;
    private final String followeeName;
    private final String status;
}