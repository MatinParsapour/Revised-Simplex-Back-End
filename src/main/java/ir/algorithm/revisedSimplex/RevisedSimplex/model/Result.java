package ir.algorithm.revisedSimplex.RevisedSimplex.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Result {

    private static int attempts = 0;
    private static Map<Integer, List<Double>> basicVariables = new LinkedHashMap<>();
    private static Map<Integer, List<Double>> nonBasicVariables = new LinkedHashMap<>();
    private static List<Double> optimalValues = new LinkedList<>();
    private static Double optimalResult = 0d;

    public static int getAttempts() {
        return attempts;
    }

    public static void setAttempts(int attempts) {
        Result.attempts = attempts;
    }

    public static Map<Integer, List<Double>> getBasicVariables() {
        return basicVariables;
    }

    public static void setBasicVariables(Map<Integer, List<Double>> basicVariables) {
        Result.basicVariables = basicVariables;
    }

    public static Map<Integer, List<Double>> getNonBasicVariables() {
        return nonBasicVariables;
    }

    public static void setNonBasicVariables(Map<Integer, List<Double>> nonBasicVariables) {
        Result.nonBasicVariables = nonBasicVariables;
    }

    public static List<Double> getOptimalValues() {
        return optimalValues;
    }

    public static void setOptimalValues(List<Double> optimalValues) {
        Result.optimalValues = optimalValues;
    }

    public static Double getOptimalResult() {
        return optimalResult;
    }

    public static void setOptimalResult(Double optimalResult) {
        Result.optimalResult = optimalResult;
    }
}
