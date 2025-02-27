package com.example.newsfeed.report.service;

import com.example.newsfeed.board.entity.Board;
import com.example.newsfeed.board.repository.BoardRepository;
import com.example.newsfeed.common.Const;
import com.example.newsfeed.report.dto.ReportListResponseDto;
import com.example.newsfeed.report.entity.Report;
import com.example.newsfeed.report.repository.ReportRepository;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void reportBoard(Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        Optional<User> user = userRepository.findById(1L);

        if (board.get().isDeleted() == true) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다.");
        }

        Report report = new Report(board.get(), user.get());
        reportRepository.save(report);

        int count = reportRepository.countByBoardId(boardId);

        if (count == Const.REPORT_COUNT) {
            boardRepository.delete(board.get());
        }
    }

    @Transactional(readOnly = true)
    public List<ReportListResponseDto> findAll() {
        List<Report> reports = reportRepository.findAll();

        return reports.stream()
                .map(report -> new ReportListResponseDto(
                        report.getId(),
                        report.getBoard().getId(),
                        report.getUser().getId(),
                        report.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}