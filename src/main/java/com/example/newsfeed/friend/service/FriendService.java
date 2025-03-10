package com.example.newsfeed.friend.service;

import com.example.newsfeed.enums.FriendStatus;
import com.example.newsfeed.friend.dto.FriendListResponseDto;
import com.example.newsfeed.friend.dto.FriendReqListResponseDto;
import com.example.newsfeed.friend.dto.FriendResponseDto;
import com.example.newsfeed.friend.entity.Friend;
import com.example.newsfeed.friend.repository.FriendRepository;
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
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional
    public FriendResponseDto sendFriend(Long followerId, Long followeeId) {
        Optional<User> follower = userRepository.findById(followerId);
        Optional<User> followee = userRepository.findById(followeeId);

        Optional<Friend> optionalFriend = friendRepository.findByFollowerIdAndFolloweeId(followerId, followeeId);

        if (optionalFriend.isPresent()) {
            if (optionalFriend.get().getStatus() == FriendStatus.REJECTED) {
                friendRepository.delete(optionalFriend.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 친구 신청을 보냈습니다.");
            }
        }

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

    @Transactional
    public FriendResponseDto updateFriendStatus(Long friendId, FriendStatus status) {
        Friend friend = friendRepository.findFriendById(friendId);
        friend.updateFriendStatus(status);
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

    @Transactional
    public void deleteFriend(Long friendId) {
        Friend friend = friendRepository.findFriendById(friendId);
        friendRepository.delete(friend);
    }

    @Transactional(readOnly = true)
    public List<FriendListResponseDto> findFriendsByUserId(Long userId) {
        List<Friend> friends = friendRepository.findFriendsByUserId(userId);

        return friends.stream()
                .map(friend -> new FriendListResponseDto(
                        friend.getFollower().getId(),
                        friend.getFollower().getName(),
                        friend.getFollowee().getId(),
                        friend.getFollowee().getName(),
                        friend.getStatus().name()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FriendReqListResponseDto> findFriendReqByUserId(Long userId) {
        List<Friend> friendRequests = friendRepository.findFriendReqByUserId(userId);

        return friendRequests.stream()
                .map(request -> new FriendReqListResponseDto(
                        request.getId(),
                        request.getFollower().getId(),
                        request.getFollower().getName(),
                        request.getStatus().name(),
                        request.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}