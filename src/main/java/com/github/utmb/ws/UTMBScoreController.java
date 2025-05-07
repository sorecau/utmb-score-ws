package com.github.utmb.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UTMBScoreController {

    @Autowired
    private UTMBScoreService utmbScoreService;

    @GetMapping("/score")
    public String getScore(@RequestParam String name) {
        return utmbScoreService.fetchRunnerScore(name);
    }
}
