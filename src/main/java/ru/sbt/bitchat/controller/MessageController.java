package ru.sbt.bitchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.bitchat.entity.MessageEntity;
import ru.sbt.bitchat.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.sbt.bitchat.dto.MessageStatus.SENT;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public static final int MESSAGE_BATCH_SIZE = 10;

    @GetMapping("/get")
    public List<MessageEntity> get(@RequestParam long id) {
        return messageRepository.getByLastNBefore(id, MESSAGE_BATCH_SIZE);
    }

    @MessageMapping("/messages")
    @SendTo("/chat/messages")
    @Retryable
    public long add(@RequestBody MessageEntity entity) {
        System.out.println("=)");
        entity.setTime(LocalDateTime.now());
        entity.setStatus(SENT);

        messagingTemplate.convertAndSend(
                "/chat/messages",
                entity
        );

        return messageRepository.saveAndFlush(entity).getId();
    }
}
