package com.example.messageapi.domain;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private Long roomId;
    private Long senderId;
    private String text;
    private LocalDateTime sentAt;
}
