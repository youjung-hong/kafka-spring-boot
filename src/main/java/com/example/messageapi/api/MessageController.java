package com.example.messageapi.api;

import com.example.messageapi.config.MessageConfig;
import com.example.messageapi.domain.Message;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {
    private final MessageConfig.MessageProducer messageProducer;
    private final MessageConfig.MessageListener messageListener;

    public MessageController(MessageConfig.MessageProducer messageProducer, MessageConfig.MessageListener messageListener) {
        this.messageProducer = messageProducer;
        this.messageListener = messageListener;
    }

    @PostMapping("/messages")
    public Mono<String> sendMessage(@RequestBody MessageSentDTO message) {
        messageProducer.sendMessage(message);
        return Mono.just("ok");
    }

    @GetMapping("/messages")
    public Mono<String> getMessage(@RequestParam Long id) {
        return Mono.just("id: " + id);
    }
}
