package com.github.utmb.ws;

import com.github.utmb.utils.MagicNameComparer;
import com.github.utmb.ws.dto.RunnerRequest;
import com.github.utmb.ws.dto.RunnerResponse;
import com.github.utmb.ws.dto.SearchResponseWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
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
    public List<RunnerResponse> getScore(@RequestBody List<RunnerRequest> runners) {
        return runners.stream()
                .map(runnerRequest -> {
                    final SearchResponseWrapper searchResponseWrapper =
                            utmbScoreService.fetchRunnerScore(runnerRequest);
                    final List<RunnerResponse> runnersHits = searchResponseWrapper.getRunners();

                    RunnerResponse bestMatchRunner = (runnersHits != null && !runnersHits.isEmpty())
                            ? runnersHits.get(0)
                            : null;

                    if (bestMatchRunner != null && areNamesEquivalent(runnerRequest, bestMatchRunner)) {
                        return bestMatchRunner;
                    } else {
                        return createZeroScoreRunner(runnerRequest.getName(), runnerRequest.getNationality());
                    }
                })
                .sorted(Comparator.comparingLong(RunnerResponse::getIp).reversed())
                .toList();
    }

    private boolean areNamesEquivalent(RunnerRequest request, RunnerResponse response) {
        return MagicNameComparer.areNamesEquivalent(StringUtils.lowerCase(request.getName()),
                StringUtils.lowerCase(response.getFullname()));
    }

    private RunnerResponse createZeroScoreRunner(String name, String nationality) {
        final RunnerResponse runnerResponse = new RunnerResponse();

        runnerResponse.setFullname(name);
        runnerResponse.setNationality(nationality);
        runnerResponse.setIp(0L);

        return runnerResponse;
    }
}
