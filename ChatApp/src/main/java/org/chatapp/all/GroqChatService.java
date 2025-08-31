package org.chatapp.all;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class GroqChatService {
    private final WebClient client;
    private final String model;

    public GroqChatService(WebClient groqWebClient, @Value("${groq.model}") String model) {
        this.client = groqWebClient;
        this.model = model;
    }

    public Mono<JsonNode> ask(String question) {
        var body = Map.of(
            "model", model,
            "messages", List.of(
                Map.of("role", "system", "content", "You are an expert Java interviewer. Answer briefly with a tiny example if helpful."),
                Map.of("role", "user", "content", question)
            ),
            "temperature", 0.2
        );

        return client.post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .retryWhen(
                    Retry.backoff(3, Duration.ofSeconds(2))
                );
    }
}
