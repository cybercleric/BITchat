package ru.sbt.bitchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;
import ru.sbt.bitchat.entity.MessageEntity;
import ru.sbt.bitchat.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ru.sbt.bitchat.dto.MessageStatus.SENT;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    public static final int MESSAGE_BATCH_SIZE = 10;

    @GetMapping("/get")
    public List<MessageEntity> get(@RequestParam long id) {
        return messageRepository.getByLastNBefore(id, MESSAGE_BATCH_SIZE);
    }

    @PutMapping("/add")
    @Retryable
    public long add(@RequestBody MessageEntity entity) {

        entity.setTime(LocalDateTime.now());
        entity.setStatus(SENT);
        return messageRepository.saveAndFlush(entity).getId();
    }
}
