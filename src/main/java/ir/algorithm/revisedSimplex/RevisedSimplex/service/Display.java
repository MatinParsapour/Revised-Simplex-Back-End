package ir.algorithm.revisedSimplex.RevisedSimplex.service;

import java.util.List;
import java.util.Map;

public interface Display {

    void matrix(String message, List<List<Double>> matrix);

    void map(String message, Map<Integer, List<Double>> map);

    void list(String message, List<Double> list);

    void bool(String message, boolean bool);

    void doubleValue(String message, double value);

    void integerValue(String message, int value);

    void message(String message);
}
