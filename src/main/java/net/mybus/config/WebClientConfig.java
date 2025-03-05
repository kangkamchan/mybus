package net.mybus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://ws.bus.go.kr/api/rest")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
