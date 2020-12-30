package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;


public class EchoSocketHandler implements WebSocketHandler {

    static Logger LOG = LoggerFactory.getLogger(EchoSocketHandler.class.getName());

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(session.receive()
                .doOnNext(WebSocketMessage::retain)
                .doOnNext(this::log));
    }

    private void log(WebSocketMessage message) {
        LOG.info("Incoming message {}", message);
    }
}