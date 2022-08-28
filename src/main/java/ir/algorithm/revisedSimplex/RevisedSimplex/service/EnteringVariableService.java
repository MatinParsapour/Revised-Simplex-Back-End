package ir.algorithm.revisedSimplex.RevisedSimplex.service;

public interface EnteringVariableService {

    boolean isResultOptimal();

    Double getEnteringVariableValue();

    int getEnteringVariableIndex();
}
