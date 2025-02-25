package com.example.newsfeed.comment.entity;

import com.example.newsfeed.board.entity.Board;
import com.example.newsfeed.common.BaseEntity;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE comment SET is_deleted = true WHERE id = ?")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private boolean isDeleted = false;

    public Comment(Board board, User user, String content) {
        this.board = board;
        this.user = user;
        this.content = content;
    }
}
