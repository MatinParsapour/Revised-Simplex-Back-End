package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Matrices;
import ir.algorithm.revisedSimplex.RevisedSimplex.model.MatricesValues;
import ir.algorithm.revisedSimplex.RevisedSimplex.model.Value;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.CalculationService;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.ValueService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ValueServiceImpl implements ValueService<MatricesValues> {

    private Matrices matrices;
    private MatricesValues data;

    @Override
    public void extract(MatricesValues data) {
        this.matrices = new Matrices();
        this.data = data;
        getTargetValues();
        getConstraintsValues();
        getBasicVariables();
        getNonBasicVariables();
        getResultValues();
        startCalculating();
    }

    private void getTargetValues() {
        Iterator<Double> iterator = data.getTargetFunctionValues().iterator();
        while(iterator.hasNext()) {
            matrices.getCoefficientsOfTargetFunction().add(iterator.next());
        }
    }

    private void getConstraintsValues(){
        Iterator<List<Double>> rowIterator = data.getConstraintsValues().iterator();
        while(rowIterator.hasNext()) {
            Iterator<Double> columnIterator = rowIterator.next().iterator();
            List<Double> variables = new LinkedList<>();
            while (columnIterator.hasNext()) {
                variables.add(columnIterator.next());
            }
            matrices.getCoefficientsOfConstraints().add(variables);
        }
    }

    private void getBasicVariables(){
        getBasicVariablesInTargetFunction();
        getBasicVariablesInConstraints();
    }

    private void getBasicVariablesInTargetFunction(){
        Iterator<Integer> iterator = data.getBasicVariablesColumns().iterator();
        while(iterator.hasNext()) {
            int basicVariableColumn = iterator.next();
            Double basicVariable = data.getTargetFunctionValues().get(basicVariableColumn);
            matrices.getBasicVariablesInTargetFunction().add(basicVariable);
        }
    }

    private void getBasicVariablesInConstraints(){
        Iterator<Integer> iterator = data.getBasicVariablesColumns().iterator();
        while (iterator.hasNext()) {
            int basicVariableColumn = iterator.next();
            List<Double> basicVariables = new LinkedList<>();
            for (int i = 0; i < matrices.getCoefficientsOfConstraints().size(); i++) {
                Double basicVariable = matrices.getCoefficientsOfConstraints().get(i).get(basicVariableColumn);
                basicVariables.add(basicVariable);
            }
            matrices.getBasicVariables().put(basicVariableColumn, basicVariables);
        }
    }

    private void getNonBasicVariables(){
        getNonBasicVariablesInTargetFunction();
        getNonBasicVariablesInConstraints();
    }

    private void getNonBasicVariablesInTargetFunction(){
        Iterator<Integer> iterator = data.getNonBasicVariablesColumns().iterator();
        while (iterator.hasNext()) {
            int nonBasicVariableColumn = iterator.next();
            Double nonBasicVariable = data.getTargetFunctionValues().get(nonBasicVariableColumn);
            matrices.getNonBasicVariablesInTargetFunction().add(nonBasicVariable);
        }
    }

    private void getNonBasicVariablesInConstraints(){
        Iterator<Integer> iterator = data.getNonBasicVariablesColumns().iterator();
        while (iterator.hasNext()) {
            int nonBasicVariableColumn = iterator.next();
            List<Double> nonBasicVariables = new LinkedList<>();
            for (int i = 0; i < matrices.getCoefficientsOfConstraints().size(); i++) {
                Double basicVariable = matrices.getCoefficientsOfConstraints().get(i).get(nonBasicVariableColumn);
                nonBasicVariables.add(basicVariable);
            }
            matrices.getNonBasicVariables().put(nonBasicVariableColumn, nonBasicVariables);
        }
    }

    private void getResultValues(){
        Iterator<Double> iterator = data.getResultValues().iterator();
        while(iterator.hasNext()) {
            matrices.getCoefficientsOfResults().add(iterator.next());
        }
    }

    private void startCalculating(){
        CalculationService calculationService = new CalculationServiceImpl(matrices);
    }

}
