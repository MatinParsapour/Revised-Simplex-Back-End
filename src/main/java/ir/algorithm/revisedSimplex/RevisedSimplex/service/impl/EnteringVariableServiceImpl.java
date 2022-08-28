package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Matrices;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.EnteringVariableService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EnteringVariableServiceImpl implements EnteringVariableService {

    private final List<List<Double>> basicVariablesInverse;
    private final List<List<Double>> plainNonBasicVariables;
    private final Map<Integer, List<Double>> nonBasicVariables;
    private final List<List<Double>> basicVariablesInverseMultipliedByNonBasicVariables = new ArrayList<>();
    private final List<Double> basicVariablesInTargetFunctionMultipliesByResultOfMultiplicationOfBasicVariablesInverseAndNonBasicVariables = new ArrayList<>();
    private final List<Double> basicVariablesInTargetFunction;
    private final List<Double> nonBasicVariablesInTargetFunction;
    private final List<Double> finalResult = new ArrayList<>();
    private Double enteringVariableValue = null;
    private int enteringVariableIndex = -1;
    private boolean isOptimal = true;

    public EnteringVariableServiceImpl(Matrices matrix, List<List<Double>> basicVariablesInverse) {
        this.basicVariablesInverse = basicVariablesInverse;
        this.nonBasicVariables = matrix.getNonBasicVariables();
        this.basicVariablesInTargetFunction = matrix.getBasicVariablesInTargetFunction();
        this.nonBasicVariablesInTargetFunction = matrix.getNonBasicVariablesInTargetFunction();
        this.plainNonBasicVariables = new LinkedList<>();
        start();
    }

    private void start() {
        InversionServiceImpl.extractListFromMap(nonBasicVariables, plainNonBasicVariables);
        multiplyBasicVariablesInverseByNonBasicVariables();
        multiplyBasicVariablesInTargetFunctionByResultOfMultiplicationOfBasicVariablesInverseAndNonBasicVariables();
        subtractionInEnteringVariableEquation();
    }

    private void multiplyBasicVariablesInverseByNonBasicVariables() {
        for (int i = 0; i < basicVariablesInverse.size(); i++) {
            int sequence = 0;
            List<Double> temp = new ArrayList<>();
            while (sequence < plainNonBasicVariables.get(0).size()) {
                double result = 0;
                for (int j = 0; j < basicVariablesInverse.size(); j++) {
                    result += basicVariablesInverse.get(i).get(j) * plainNonBasicVariables.get(j).get(sequence);
                }
                temp.add(result);
                sequence++;
            }
            basicVariablesInverseMultipliedByNonBasicVariables.add(temp);
        }
    }

    private void multiplyBasicVariablesInTargetFunctionByResultOfMultiplicationOfBasicVariablesInverseAndNonBasicVariables() {
        int sequence = 0;
        while (sequence < basicVariablesInverseMultipliedByNonBasicVariables.get(0).size()) {
            double result = 0;
            for (int i = 0; i < basicVariablesInTargetFunction.size(); i++) {
                result += basicVariablesInTargetFunction.get(i) * basicVariablesInverseMultipliedByNonBasicVariables.get(i).get(sequence);
            }
            basicVariablesInTargetFunctionMultipliesByResultOfMultiplicationOfBasicVariablesInverseAndNonBasicVariables.add(result);
            sequence++;
        }
    }

    private void subtractionInEnteringVariableEquation(){
        for (int i = 0; i < basicVariablesInTargetFunctionMultipliesByResultOfMultiplicationOfBasicVariablesInverseAndNonBasicVariables.size(); i++) {
            double result = basicVariablesInTargetFunctionMultipliesByResultOfMultiplicationOfBasicVariablesInverseAndNonBasicVariables.get(i) - nonBasicVariablesInTargetFunction.get(i);
            finalResult.add(result);
        }
    }

    private void calculateSmallestNumber(){
        if (isOptimal) {
            return;
        }

        double min = Integer.MAX_VALUE;
        int indexOfMin = -1;
        for (int i = 0; i < finalResult.size(); i++) {
            boolean isItLessThanMin = finalResult.get(i) < min;
            if (isItLessThanMin) {
                min = finalResult.get(i);
                indexOfMin = i;
            }
        }
        enteringVariableValue = min;
        enteringVariableIndex = indexOfMin;
    }

    @Override
    public boolean isResultOptimal() {
        for (Double element : finalResult) {
            if (element < 0) {
                isOptimal = false;
                calculateSmallestNumber();
                break;
            }
        }
        return isOptimal;
    }

    @Override
    public Double getEnteringVariableValue() {
        return enteringVariableValue;
    }

    @Override
    public int getEnteringVariableIndex() {
        return enteringVariableIndex;
    }


}
