package com.example.newsfeed.comment.repository;

import com.example.newsfeed.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    default Comment findCommentById(Long commentId) {
        return findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디를 찾을 수 없습니다."));
    }

    Optional<Comment> findCommentByBoardIdAndId(Long boardId, Long commentId);

    @Query("SELECT c FROM Comment c INNER JOIN User u ON c.user.id = u.id WHERE c.isDeleted = false AND c.board.id = :boardId")
    List<Comment> findCommentsByBoardId(@Param("boardId") Long boardId);
}