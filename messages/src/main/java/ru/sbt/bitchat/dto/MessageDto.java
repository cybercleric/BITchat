package ru.sbt.bitchat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class MessageDto {
    private long id;
    private long authorId;
    private LocalDateTime time;
    private String content;
    private MessageStatus status;

//    public static MessageDto fromEntity(MessageEntity entity) {
//        return MessageDto.builder()
//                .id(entity.getId())
//                .authorId()
//    }
}
