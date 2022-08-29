package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Matrices;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.LeavingVariableService;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeavingVariableServiceImpl implements LeavingVariableService {

    private final List<List<Double>> basicVariablesInverse;
    private List<Double> coefficientsOfEnteringVariable = new LinkedList<>();

    /**
     * Result of multiplication between coefficients of entering variable and  basic variables inverse
     */
    private final List<Double> multiplicationResultOfCEVAndBV = new LinkedList<>();

    /**
     * Result of multiplication between result matrix and basic variables inverse
     */
    private final List<Double> multiplicationResultOfRAndBV = new LinkedList<>();

    /**
     *                     result of multiplication between result matrix and basic variables inverse
     * divided = ---------------------------------------------------------------------------------------------------------
     *            result of multiplication between between coefficients of entering variable and basic variables inverse
     */
    private final List<Double> divided = new LinkedList<>();
    private final int indexOfEnteringVariable;
    private final Matrices matrix;
    private final List<Double> resultMatrix;
    private double minimumValue;
    private int minimumIndex;

    public LeavingVariableServiceImpl(List<Double> resultMatrix,
                                      int indexOfEnteringVariable,
                                      List<List<Double>> basicVariablesInverse,
                                      Matrices matrix) {
        this.indexOfEnteringVariable = indexOfEnteringVariable;
        this.basicVariablesInverse = basicVariablesInverse;
        this.resultMatrix = resultMatrix;
        this.matrix = matrix;
        start();
    }

    private void start(){
        getListOfCoefficientsOfEnteringVariable();
        multiplyLists(coefficientsOfEnteringVariable, multiplicationResultOfCEVAndBV);
        multiplyLists(resultMatrix, multiplicationResultOfRAndBV);
        division(multiplicationResultOfRAndBV, multiplicationResultOfCEVAndBV);
        findMinimum();
    }

    private void getListOfCoefficientsOfEnteringVariable() {
        int index = 0;
        for (Map.Entry me: matrix.getNonBasicVariables().entrySet()) {
            if (index == indexOfEnteringVariable) {
                coefficientsOfEnteringVariable = matrix.getNonBasicVariables().get(me.getKey());
                break;
            }
            index++;
        }
    }

    private void multiplyLists(List<Double> multiplicative, List<Double> resultList) {
        for (int i = 0; i < basicVariablesInverse.size(); i++) {
            double result = 0;
            for (int j = 0; j < multiplicative.size(); j++) {
                result += multiplicative.get(j) * basicVariablesInverse.get(j).get(i);
            }
            resultList.add(result);
        }
    }

    private void division(List<Double> divider, List<Double> divisible) {
        for (int i = 0; i < divider.size(); i++) {
            double result = divider.get(i) / divisible.get(i);
            divided.add(result);
        }
    }

    private void findMinimum(){
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < divided.size(); i++) {
            if (divided.get(i) < min) {
                min = divided.get(i);
                minimumValue = divided.get(i);
                minimumIndex = i;
            }
        }
    }

    @Override
    public double getMinimumValue() {
        return minimumValue;
    }

    @Override
    public int getMinimumIndex() {
        return minimumIndex;
    }
}
