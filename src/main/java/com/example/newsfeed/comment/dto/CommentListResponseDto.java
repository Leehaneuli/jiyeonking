package com.example.newsfeed.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentListResponseDto {

    private final Long id;
    private final Long boardId;
    private final String name;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
}