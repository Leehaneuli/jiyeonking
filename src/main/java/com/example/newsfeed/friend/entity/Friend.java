package com.example.newsfeed.friend.entity;

import com.example.newsfeed.common.BaseEntity;
import com.example.newsfeed.enums.FriendStatus;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friend")
@Getter
@NoArgsConstructor
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followee_id")
    private User followee;

    @Enumerated(EnumType.STRING)
    private FriendStatus status = FriendStatus.PENDING;

    public Friend(User follower, User followee) {
        this.follower = follower;
        this.followee = followee;
    }

    public void updateFriendStatus(FriendStatus status) {
        this.status = status;
    }
}
