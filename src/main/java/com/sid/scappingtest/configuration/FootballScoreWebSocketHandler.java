package com.sid.scappingtest.configuration;

import com.sid.scappingtest.service.FootballScoreScraperService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;

@Component
public class FootballScoreWebSocketHandler extends TextWebSocketHandler implements WebSocketHandler {

    private final FootballScoreScraperService scraperService;

    public FootballScoreWebSocketHandler(FootballScoreScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        scraperService.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        scraperService.removeSession(session);
    }
}
