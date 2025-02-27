package com.example.newsfeed.comment.controller;

import com.example.newsfeed.comment.dto.CommentRequestDto;
import com.example.newsfeed.comment.dto.CommentResponseDto;
import com.example.newsfeed.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long boardId,
                                                            @Valid @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.createComment(boardId, commentRequestDto.getContent());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long boardId,
                                                            @PathVariable Long commentId,
                                                            @Valid @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(boardId, commentId, commentRequestDto.getContent());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long boardId,
                                                @PathVariable Long commentId) {
        commentService.deleteComment(boardId, commentId);

        return new ResponseEntity<>("댓글 삭제 완료", HttpStatus.OK);
    }
}