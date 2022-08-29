package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.service.InversionService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InversionServiceImpl implements InversionService {

    private final Map<Integer, List<Double>> basicVariables;
    private final List<List<Double>> basicVariablesInverse;
    private final int numberOfColumns;

    public InversionServiceImpl (Map<Integer, List<Double>> basicVariables) {
        this.basicVariables = basicVariables;
        this.numberOfColumns = basicVariables.get(getAKey()).size();
        this.basicVariablesInverse = new LinkedList<>();
        start();
    }

    private void start(){
        extractListFromMap(basicVariables, basicVariablesInverse);
        addDiagonalMatrix();
        inverseTheMatrix();
        createMatrixInverse();
    }

    public static void extractListFromMap(Map<Integer, List<Double>> map, List<List<Double>> matrix) {
        int firstKey = 0;
        for (Integer key: map.keySet()) {
            firstKey = key;
            break;
        }
        for (int i = 0; i < map.get(firstKey).size(); i++) {
            List<Double> tempList = new LinkedList<>();
            for (Integer key: map.keySet()) {
                tempList.add(map.get(key).get(i));
            }
            matrix.add(tempList);
        }
    }

    private void createMatrixInverse(){
        for (int i = 0; i < basicVariablesInverse.size(); i++) {
            List<Double> rowValuesOfMatrixInverse = new ArrayList<>();
            for (int j = numberOfColumns; j < basicVariablesInverse.get(i).size(); j++) {
                rowValuesOfMatrixInverse.add(basicVariablesInverse.get(i).get(j));
            }
            basicVariablesInverse.set(i, rowValuesOfMatrixInverse);
        }
    }

    private void addDiagonalMatrix(){
        int indexOfOne = 0;
        while (indexOfOne < basicVariablesInverse.size()) {
            for (int i = 0; i < basicVariablesInverse.size(); i++) {
                double valueOfIndexInDiagonalMatrix = i == indexOfOne ? 1 : 0;
                basicVariablesInverse.get(i).add(valueOfIndexInDiagonalMatrix);
            }
            indexOfOne++;
        }
    }

    private int getAKey(){
        int key = -1;
        for (Map.Entry me: basicVariables.entrySet()) {
            key = (Integer) me.getKey();
        }
        return key;
    }
    private void inverseTheMatrix(){
        int diagonalIndex = 0;
        while (diagonalIndex < basicVariablesInverse.size()) {
            boolean isValueOfDiagonalIndexOneOrZero = basicVariablesInverse.get(diagonalIndex).get(diagonalIndex) == 1 || basicVariablesInverse.get(diagonalIndex).get(diagonalIndex) == 0;
            if (!isValueOfDiagonalIndexOneOrZero) {
                setMultipliedListInBasicVariablesInverse(diagonalIndex);
            }
            for (int i = 0; i < basicVariablesInverse.size(); i++) {
                if (i != diagonalIndex) {
                    setSubtractedListToBasicVariablesInverse(diagonalIndex, i);
                }
            }
            diagonalIndex++;
        }
    }

    private void setMultipliedListInBasicVariablesInverse(int diagonalIndex) {
        double valueOfDiagonalIndex = Double.parseDouble(String.valueOf(basicVariablesInverse.get(diagonalIndex).get(diagonalIndex)));
        List<Double> multipliedList = multiplyList(basicVariablesInverse.get(diagonalIndex),(1/valueOfDiagonalIndex));
        basicVariablesInverse.set(diagonalIndex,multipliedList);
    }

    private void setSubtractedListToBasicVariablesInverse(int diagonalIndex, int i) {
        List<Double> subtractedList = subtractLists(basicVariablesInverse.get(diagonalIndex), basicVariablesInverse.get(i), basicVariablesInverse.get(i).get(diagonalIndex));
        basicVariablesInverse.set(i, subtractedList);
    }

    private List<Double> subtractLists(List<Double> subtractor, List<Double> subtractive, double coefficient){
        List<Double> subtractedList = new ArrayList<>();
        for (int i = 0; i < subtractor.size(); i++) {
            subtractedList.add(subtractive.get(i) - (coefficient * subtractor.get(i)));
        }
        return subtractedList;
    }

    private List<Double> multiplyList(List<Double> list, double number) {
        return list.stream().map(element -> element*number).collect(Collectors.toList());
    }

    @Override
    public List<List<Double>> getBasicVariablesInverse() {
        return basicVariablesInverse;
    }
}
