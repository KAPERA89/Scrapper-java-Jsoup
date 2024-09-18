package com.sid.scappingtest.controller;

import com.sid.scappingtest.service.FootballScoreScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FootballScoreRestController {
    @Autowired
    private FootballScoreScraperService footballScoreScraperService;

    @GetMapping("/scores")
    public List<String> getScores() throws IOException {
        return footballScoreScraperService.getLiveScores();
    }
}
