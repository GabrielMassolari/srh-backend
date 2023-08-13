package com.srh.api.algorithms.resources;

import com.srh.api.algorithms.strategies.*;
import com.srh.api.error.exception.InvalidAlgorithmRecommendationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmStrategyAsync {
    @Autowired
    private CollaborativeAsync collaborativeAsync;

    @Autowired
    private ContentBasedAsync contentBasedAsync;

    @Autowired
    private WeightedHybridAsync weightedHybridAsync;

    @Autowired
    private MixedHybridAsync mixedHybridAsync;

    @SneakyThrows
    public RecommendationAlgorithm getAlgorithm(Integer algorithmId) {
        String algorithmValue = String.valueOf(algorithmId);

        switch (algorithmValue) {
            case "1":
                return collaborativeAsync;
            case "2":
                return contentBasedAsync;
            case "3":
                return weightedHybridAsync;
            case "4":
                return mixedHybridAsync;
            default:
                throw new InvalidAlgorithmRecommendationException("Invalid algorithm");
        }
    }
}
