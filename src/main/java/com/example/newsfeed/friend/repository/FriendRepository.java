package com.example.newsfeed.friend.repository;

import com.example.newsfeed.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    default Friend findFriendById(Long friendId) {
        return findById(friendId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디를 찾을 수 없습니다."));
    }

    @Query("SELECT f FROM Friend f INNER JOIN User fr ON f.follower.id = fr.id INNER JOIN User fe ON f.followee.id = fe.id WHERE (f.follower.id = :userId OR f.followee.id = :userId) AND f.status = APPROVED")
    List<Friend> findFriendsByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM Friend f INNER JOIN User u on f.follower.id = u.id WHERE f.followee.id = :userId AND f.status = PENDING")
    List<Friend> findFriendReqByUserId(@Param("userId") Long userId);
}