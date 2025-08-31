package org.chatapp.all;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GroqConfig {

    @Bean
    WebClient groqWebClient(
        @Value("${groq.base-url}") String baseUrl,
        @Value("${groq.api-key}") String apiKey
    ) {
        System.out.println("Base URL: " + baseUrl);
        System.out.println("Groq API Key loaded successfully!");

        return WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .exchangeStrategies(
                ExchangeStrategies.builder()
                    .codecs(c -> c.defaultCodecs().maxInMemorySize(8 * 1024 * 1024))
                    .build()
            )
            .build();
    }
}

