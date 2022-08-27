package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Matrices;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.DisplacementService;

import java.util.List;
import java.util.Map;

public class DisplacementServiceImpl implements DisplacementService {

    private final int indexOfEnteringVariable;
    private final int indexOfLeavingVariable;
    private final Matrices matrix;

    public DisplacementServiceImpl(Matrices matrix,
                                   int indexOfEnteringVariable,
                                   int indexOfLeavingVariable) {
        this.matrix = matrix;
        this.indexOfEnteringVariable = indexOfEnteringVariable;
        this.indexOfLeavingVariable = indexOfLeavingVariable;
        start();
    }

    private void start(){
        displacementOfTargetFunctionBasicVariablesAndNonBasicVariables();
        displacementOfConstraintsBasicVariablesAndNonBasicVariables();

    }

    private void displacementOfTargetFunctionBasicVariablesAndNonBasicVariables(){
        double leave = matrix.getBasicVariablesInTargetFunction().get(indexOfLeavingVariable);
        double enter = matrix.getNonBasicVariablesInTargetFunction().get(indexOfEnteringVariable);
        matrix.getBasicVariablesInTargetFunction().set(indexOfLeavingVariable, enter);
        matrix.getNonBasicVariablesInTargetFunction().set(indexOfEnteringVariable, leave);

    }

    private void displacementOfConstraintsBasicVariablesAndNonBasicVariables(){
        // Get key for entering and leaving variables
        int keyForEnteringVariable = getKey(matrix.getBasicVariables(), indexOfLeavingVariable);
        int keyForLeavingVariable = getKey(matrix.getNonBasicVariables(), indexOfEnteringVariable);

        // Get list of entering and leaving variables values
        List<Double> leavingVariableValues = matrix.getBasicVariables().get(keyForEnteringVariable);
        List<Double> enteringVariableValues = matrix.getNonBasicVariables().get(keyForLeavingVariable);

        //Swap lists
        matrix.getBasicVariables().put(keyForLeavingVariable, enteringVariableValues);
        matrix.getNonBasicVariables().put(keyForEnteringVariable, leavingVariableValues);
        matrix.getBasicVariables().remove(keyForEnteringVariable);
        matrix.getNonBasicVariables().remove(keyForLeavingVariable);
    }

    private int getKey(Map<Integer, List<Double>> map, int requiredIndex) {
        int key = -1;
        int index = 0;
        for (Map.Entry me: map.entrySet()) {
            if (index == requiredIndex) {
                key = (Integer) me.getKey();
                break;
            }

            index++;
        }
        return key;
    }
}
