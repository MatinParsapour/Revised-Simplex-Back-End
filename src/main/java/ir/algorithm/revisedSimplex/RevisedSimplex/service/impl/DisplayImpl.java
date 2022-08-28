package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.service.Display;

import java.util.List;
import java.util.Map;

public class DisplayImpl implements Display {


    @Override
    public void matrix(String message, List<List<Double>> matrix) {
        System.out.println(message);
        for (List<Double> list : matrix) {
            for (Double value : list) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    @Override
    public void map(String message, Map<Integer, List<Double>> map) {
        System.out.println(message);
        for (Map.Entry me: map.entrySet()) {
            for (Object value: (List<Double>) me.getValue()) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void list(String message, List<Double> list) {
        System.out.println(message);
        for (Double element: list) {
            System.out.print(element + " ");
        }
        System.out.println("\n");
    }

    @Override
    public void bool(String message, boolean bool) {
        String result = bool ? "Yes" : "No";
        System.out.println(message + result + "\n");
    }

    @Override
    public void doubleValue(String message, double value) {
        System.out.println(message + value + "\n");
    }

    @Override
    public void integerValue(String message, int value) {
        System.out.println(message + value + "\n");
    }

    @Override
    public void message(String message) {
        System.out.println(message + "\n");
    }
}
