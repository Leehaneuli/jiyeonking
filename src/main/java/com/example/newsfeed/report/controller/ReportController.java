package com.example.newsfeed.report.controller;

import com.example.newsfeed.report.dto.ReportListResponseDto;
import com.example.newsfeed.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 게시물 신고
    @PostMapping("/boards/{boardId}/reports")
    public ResponseEntity<String> reportBoard(@PathVariable Long boardId) {
        reportService.reportBoard(boardId);

        return new ResponseEntity<>("신고 완료", HttpStatus.OK);
    }

    // 신고 전체 조회
    @GetMapping("/admins/reports")
    public ResponseEntity<List<ReportListResponseDto>> findAll() {
        List<ReportListResponseDto> reports = reportService.findAll();

        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}