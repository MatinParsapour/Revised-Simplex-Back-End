package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Matrices;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.OptimalService;

import java.util.ArrayList;
import java.util.List;

public class OptimalServiceImpl implements OptimalService {

    private final List<List<Double>> basicVariablesInverse;
    private final List<Double> resultMatrix;
    private final List<Double> optimalVariable = new ArrayList<>();
    private double optimalResult = 0;
    private final List<Double> coefficientsOfBasicVariablesInTargetFunction;

    public OptimalServiceImpl(Matrices matrix,
                              List<Double> resultMatrix,
                              List<List<Double>> basicVariablesInverse){
        this.basicVariablesInverse = basicVariablesInverse;
        this.resultMatrix = resultMatrix;
        this.coefficientsOfBasicVariablesInTargetFunction = matrix.getBasicVariablesInTargetFunction();
        start();
    }

    private void start(){
        optimalVariable();
        optimalResult();
    }

    private void optimalVariable(){
        for(int i = 0; i < basicVariablesInverse.size(); i++) {
            double result = 0;
            for (int j = 0; j < resultMatrix.size(); j++) {
                result += resultMatrix.get(j) * basicVariablesInverse.get(i).get(j);
            }
            optimalVariable.add(result);
        }
    }

    private void optimalResult(){
        for (int i = 0; i < coefficientsOfBasicVariablesInTargetFunction.size(); i++) {
            optimalResult += coefficientsOfBasicVariablesInTargetFunction.get(i) * optimalVariable.get(i);
        }
    }

    @Override
    public List<Double> getOptimalVariable() {
        return optimalVariable;
    }

    @Override
    public double getOptimalResult() {
        return optimalResult;
    }
}
