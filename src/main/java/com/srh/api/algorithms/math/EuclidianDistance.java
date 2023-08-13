package com.srh.api.algorithms.math;

import java.util.List;

public class EuclidianDistance {
    public Double calc(List<Coordinate> coordinates) {
        Double sumDifferencePairs = 0.0;

        for (Coordinate coordinate : coordinates) {
            sumDifferencePairs += calculePair(coordinate.getX(), coordinate.getY());
        }

        return Math.sqrt(sumDifferencePairs);
    }

    private Double calculePair(Double x, Double y) {
        if (x == null || y == null)
            return 0.0;

        double difference = x - y;
        return Math.pow(difference, 2);
    }


}
