package com.example.newsfeed.report.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReportListResponseDto {

    private final Long id;
    private final Long boardId;
    private final Long reporter;
    private final LocalDateTime createdAt;
}
