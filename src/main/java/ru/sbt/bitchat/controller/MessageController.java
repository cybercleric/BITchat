package ru.sbt.bitchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.bitchat.entity.MessageEntity;
import ru.sbt.bitchat.repository.MessageRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    @GetMapping("")
    public List<MessageEntity> get(@RequestParam long messageId) {
        return messageRepository.findAll();
    }
}
