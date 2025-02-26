package com.example.newsfeed.friend.controller;

import com.example.newsfeed.friend.dto.FriendListResponseDto;
import com.example.newsfeed.friend.dto.FriendRequestDto;
import com.example.newsfeed.friend.dto.FriendResponseDto;
import com.example.newsfeed.friend.service.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 추가
    @PostMapping
    public ResponseEntity<FriendResponseDto> sendFriend(@Valid @RequestBody FriendRequestDto friendRequestDto) {
        FriendResponseDto friendResponseDto = friendService.sendFriend(1L, friendRequestDto.getUserId());

        return new ResponseEntity<>(friendResponseDto, HttpStatus.OK);
    }

    // 친구 수락
    @PatchMapping("/{friendId}/accept")
    public ResponseEntity<FriendResponseDto> acceptFriend(@PathVariable Long friendId) {
        FriendResponseDto friendResponseDto = friendService.acceptFriend(friendId);

        return new ResponseEntity<>(friendResponseDto, HttpStatus.OK);
    }

    // 친구 삭제
    @DeleteMapping("/{friendId}")
    public ResponseEntity<String> deleteFriend(@PathVariable Long friendId) {
        friendService.deleteFriend(friendId);

        return new ResponseEntity<>("친구 삭제 완료", HttpStatus.OK);
    }

    // 친구 목록 전체 조회
    @GetMapping
    public ResponseEntity<List<FriendListResponseDto>> findFriendsByUserId() {
        List<FriendListResponseDto> friends = friendService.findFriendsByUserId(2L);

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }
}