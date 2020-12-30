package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Socket Chat tests")
public class ChatSocketHandlerTest {

    @LocalServerPort
    private String port;

    @Test
    @DisplayName("Should Echo every message except the first one")
    public void shouldEchoAllTheMessagesExceptFirstOne()
            throws URISyntaxException {
        int count = 10;

        WebSocketClient client = new ReactorNettyWebSocketClient();

        Flux<String> input = Flux.range(1, count)
                .map(l -> String
                        .format("{\"username\":\"user\",\"content\":\"HI -%d\"}", l));

        ReplayProcessor<Object> output = ReplayProcessor.create(count);

        client.execute(new URI("ws://localhost:" + port + "/chat"), session -> session
                .send(input.map(session::textMessage))
                .thenMany(session.receive()
                        .take(count)
                        .map(WebSocketMessage::getPayloadAsText))
                .subscribeWith(output)
                .then())
                .block(Duration.ofMillis(5000));

        assertEquals(input.skip(1).collectList().block(Duration.ofMillis(5000))
                , output.skip(1).collectList().block(Duration.ofMillis(5000)));
    }

    @Test
    @DisplayName("The First Message Should Be The JoinMessage")
    public void firstMessageShouldBeTheJoinMessage() throws URISyntaxException {
        int count = 1;

        WebSocketClient client = new ReactorNettyWebSocketClient();

        Flux<String> input = Flux.range(1, count)
                .map(l -> String
                        .format("{\"username\":\"user one\",\"content\":\"\"}"));

        ReplayProcessor<Object> output = ReplayProcessor.create(count);

        client.execute(new URI("ws://localhost:" + port + "/chat"), session -> session
                .send(input.map(session::textMessage))
                .thenMany(session
                        .receive()
                        .take(count)
                        .map(WebSocketMessage::getPayloadAsText))
                .subscribeWith(output)
                .then())
                .block(Duration.ofMillis(5000));

        assertEquals("{\"username\":\"system\",\"content\":\"user one joined your channel\"}"
                , output.collectList().block(Duration.ofMillis(5000)).get(0));
    }
}
