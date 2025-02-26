package com.example.newsfeed.friend.service;

import com.example.newsfeed.friend.dto.FriendResponseDto;
import com.example.newsfeed.friend.entity.Friend;
import com.example.newsfeed.friend.repository.FriendRepository;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional
    public FriendResponseDto sendFriend(Long followerId, Long followeeId) {
        Optional<User> follower = userRepository.findById(followerId);
        Optional<User> followee = userRepository.findById(followeeId);

        Friend friend = new Friend(follower.get(), followee.get());
        Friend savedFriend = friendRepository.save(friend);

        return new FriendResponseDto(
                savedFriend.getId(),
                savedFriend.getFollower().getId(),
                savedFriend.getFollowee().getId(),
                savedFriend.getStatus().name(),
                savedFriend.getCreatedAt(),
                savedFriend.getModifiedAt()
        );
    }
}