package com.github.utmb.ws;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UTMBScoreService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchRunnerScore(String runnerName) {
        String url = "https://api.utmb.world/search/runners?search=" + runnerName;

        return restTemplate.getForObject(url, String.class);
    }
}
