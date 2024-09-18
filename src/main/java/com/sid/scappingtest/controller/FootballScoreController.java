package com.sid.scappingtest.controller;

import com.sid.scappingtest.service.FootballScoreScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FootballScoreController {
    @Autowired
    private FootballScoreScraperService footballScoreScraperService;

    @GetMapping("/scores-page")
    public String showedScore() {
        return "scores";
    }

}
