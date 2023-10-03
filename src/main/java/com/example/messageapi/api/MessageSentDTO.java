package com.example.messageapi.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageSentDTO {
    private Long roomId;
    private Long senderId;
    private String text;

    @Override
    public String toString() {
        return """
                {"roomId": %d, "senderId": %d, "text": %s}""".formatted(roomId, senderId, text);
    }
}
