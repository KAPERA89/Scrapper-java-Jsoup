package com.sid.scappingtest.configuration;

import com.sid.scappingtest.service.FootballScoreScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final FootballScoreWebSocketHandler footballScoreWebSocketHandler;

    public WebSocketConfig(FootballScoreWebSocketHandler footballScoreWebSocketHandler) {
        this.footballScoreWebSocketHandler = footballScoreWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(footballScoreWebSocketHandler, "/ws/scores")
                .setAllowedOrigins("*");
    }
}
