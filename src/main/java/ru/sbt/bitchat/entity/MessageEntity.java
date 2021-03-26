package ru.sbt.bitchat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sbt.bitchat.dto.MessageStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "messages", uniqueConstraints = {@UniqueConstraint(columnNames = "idempotenceId")})
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private long id;
    private UUID idempotenceId;
    private long authorId;
    private LocalDateTime time;
    private String content;
    private MessageStatus status;
}
