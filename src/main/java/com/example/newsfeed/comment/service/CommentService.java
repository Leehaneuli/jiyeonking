package com.example.newsfeed.comment.service;

import com.example.newsfeed.board.entity.Board;
import com.example.newsfeed.board.repository.BoardRepository;
import com.example.newsfeed.comment.dto.CommentResponseDto;
import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.comment.repository.CommentRepository;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDto createComment(Long boardId, String content) {
        Optional<Board> board = boardRepository.findById(boardId);
        Optional<User> user = userRepository.findById(1L);

        Comment comment = new Comment(board.get(), user.get(), content);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    @Transactional
    public CommentResponseDto updateComment(Long boardId, Long commentId, String content) {
        Comment comment = commentRepository.findCommentByBoardIdAndId(boardId, commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));

        comment.updateContent(content);

        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Transactional
    public void deleteComment(Long boardId, Long commentId) {
        Comment comment = commentRepository.findCommentByBoardIdAndId(boardId, commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));

        commentRepository.delete(comment);
    }
}