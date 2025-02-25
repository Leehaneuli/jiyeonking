package com.example.newsfeed.board.entity;

import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.common.BaseEntity;
import com.example.newsfeed.report.entity.Report;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE board SET is_deleted = true WHERE id = ?")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    public Board(User user, String content) {
        this.user = user;
        this.content = content;
    }
}
