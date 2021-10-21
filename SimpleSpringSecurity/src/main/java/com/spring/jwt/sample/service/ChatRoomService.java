package com.spring.jwt.sample.service;

import com.spring.jwt.sample.model.chat.ChatRoom;
import com.spring.jwt.sample.repository.redis.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAllRoom();
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomRepository.findRoomById(id);
    }

    public ChatRoom createChatRoom(String name) {
        return chatRoomRepository.createChatRoom(name);
    }


}
