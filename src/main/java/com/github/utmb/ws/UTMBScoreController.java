package com.github.utmb.ws;

import com.github.utmb.ws.dto.RunnerEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UTMBScoreController {

    @Autowired
    private UTMBScoreService utmbScoreService;

    @PostMapping(
            value = "/score",
            consumes = "application/json",
            produces = "application/json")
    public String getScore(@RequestBody List<RunnerEntryDTO> runners) {
        return utmbScoreService.fetchRunnerScore("catalin");
    }
}
