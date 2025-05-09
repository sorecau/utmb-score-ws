package com.github.utmb.ws;

import com.github.utmb.ws.dto.RunnerRequest;
import com.github.utmb.ws.dto.SearchResponseWrapper;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UTMBScoreService {

    private final RestTemplate restTemplate = new RestTemplate();

    public SearchResponseWrapper fetchRunnerScore(RunnerRequest dto) {
        final String nationality = dto.getNationality();
        final String name = dto.getName();

        String url = "https://api.utmb.world/search/runners?"
                + "category=general"
                + "&nationality=" + (StringUtils.isNotEmpty(nationality) ? nationality : "")
                + "&limit=1"
                + "&offset=0"
                + "&search=" + name;

        return restTemplate.getForObject(url, SearchResponseWrapper.class);
    }
}
