package com.example.newsfeed.friend.controller;

import com.example.newsfeed.friend.dto.FriendRequestDto;
import com.example.newsfeed.friend.dto.FriendResponseDto;
import com.example.newsfeed.friend.service.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}