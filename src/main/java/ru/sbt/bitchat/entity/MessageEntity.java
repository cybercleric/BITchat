package ru.sbt.bitchat.entity;

import lombok.Data;
import ru.sbt.bitchat.dto.MessageStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private long id;
    private long authorId;
    private LocalDateTime time;
    private String content;
    private MessageStatus status;
}
