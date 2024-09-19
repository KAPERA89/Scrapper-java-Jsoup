package com.sid.scappingtest.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class FootballScoreScraperService {

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public List<String> getLiveScores() throws IOException {
        List<String> scores = new ArrayList<>();

        Document doc = Jsoup.connect("https://www.bbc.com/sport/football/scores-fixtures").get();
        Elements elements = doc.select("ul.ssrcss-1w89ukb-StackLayout li.ssrcss-3zjzjc-HeadToHeadWrapper");
        for (Element element : elements) {
            String homeTeam = element.select(".ssrcss-93qnvo-StyledTeam-HomeTeam span.ssrcss-1p14tic-DesktopValue").text();
            String awayTeam = element.select(".ssrcss-19knk4k-StyledTeam-AwayTeam span.ssrcss-1p14tic-DesktopValue").text();
            String homeScore = element.select(".ssrcss-qsbptj-HomeScore").text();
            String awayScore = element.select(".ssrcss-fri5a2-AwayScore").text();
            String matchProgress = element.select(".ssrcss-196en8t-StyledPeriod div").text();

            String scoreDetails = homeTeam + " " + homeScore + " - " + awayScore + " " + awayTeam + " (" + matchProgress + ")";
            scores.add(scoreDetails);
        }

        return scores;
    }

    @Scheduled(fixedRate = 1000)
    public void scrapeAndPushScores() {
        try {
            List<String> liveScores = getLiveScores();
            String scoresMessage = String.join("\n", liveScores);

            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(scoresMessage));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }
}
