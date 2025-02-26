package com.example.newsfeed.friend.repository;

import com.example.newsfeed.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    default Friend findFriendById(Long friendId) {
        return findById(friendId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디를 찾을 수 없습니다."));
    }
}