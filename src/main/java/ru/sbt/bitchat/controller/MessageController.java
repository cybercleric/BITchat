package ru.sbt.bitchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.sbt.bitchat.entity.MessageEntity;
import ru.sbt.bitchat.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    @GetMapping("/messageId")
    public List<MessageEntity> get(@RequestParam long messageId) {
        int n = 20;
        LocalDateTime timeStamp = messageRepository.findById(messageId).get().getTime();
        List<MessageEntity> messages = messageRepository
                .findAll(Sort.by(Sort.Direction.DESC, "time"))
                .stream()
                .filter((message) -> message.getTime().compareTo(timeStamp) <= 0)
                .limit(n)
                .collect(Collectors.toList());
        return messages;
    }

    @PostMapping
    public long add(@RequestBody MessageEntity entity) {
        return 0;
    }
}
