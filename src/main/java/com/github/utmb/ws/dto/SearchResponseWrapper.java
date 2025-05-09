package com.github.utmb.ws.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponseWrapper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<RunnerResponse> runners;

    public List<RunnerResponse> getRunners() {
        return runners;
    }

    public void setRunners(List<RunnerResponse> runners) {
        this.runners = runners;
    }
}
