package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Matrices;
import ir.algorithm.revisedSimplex.RevisedSimplex.model.Result;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.*;

public class CalculationServiceImpl implements CalculationService {

    private final Matrices matrix;
    private final Display display;

    public CalculationServiceImpl(Matrices matrix) {
        this.matrix = matrix;
        this.display = new DisplayImpl();
        start();
    }

    private void start() {
        EnteringVariableService enteringVariableService;
        int turn = 1;
        do {
            display.message("Try number " + turn);

            //Data
            display.map("Basic variables in constraints ", matrix.getBasicVariables());
            display.map("Non-Basic variables in constraints ", matrix.getNonBasicVariables());
            display.list("Basic variables in target function ", matrix.getBasicVariablesInTargetFunction());
            display.list("Non-Basic variables in target function ", matrix.getNonBasicVariablesInTargetFunction());

            //Inversion
            InversionService inversionService = new InversionServiceImpl(matrix.getBasicVariables());
            display.matrix("Basic variable inverse", inversionService.getBasicVariablesInverse());

            //Entering variable
            enteringVariableService = new EnteringVariableServiceImpl(matrix, inversionService.getBasicVariablesInverse());
            boolean isResultOptimal = enteringVariableService.isResultOptimal();
            display.bool("Is result optimal ", isResultOptimal);

            // If result is optimal
            if (isResultOptimal) {
                OptimalService optimalService = new OptimalServiceImpl(matrix,
                        matrix.getCoefficientsOfResults(),
                        inversionService.getBasicVariablesInverse());
                Result.setAttempts(turn);
                Result.setOptimalResult(optimalService.getOptimalResult());
                Result.setBasicVariables(matrix.getBasicVariables());
                Result.setNonBasicVariables(matrix.getNonBasicVariables());
                Result.setOptimalValues(optimalService.getOptimalVariable());
                display.list("Optimal variable " + matrix.getBasicVariables(), optimalService.getOptimalVariable());
                display.doubleValue("Optimal result ", optimalService.getOptimalResult());
                break;
            }
            // If result is not optimal
            display.doubleValue("Value of entering variable ", enteringVariableService.getEnteringVariableValue());
            display.integerValue("Index of entering variable ", enteringVariableService.getEnteringVariableIndex());

            //Leaving variable
            LeavingVariableService leavingVariableService = new LeavingVariableServiceImpl(matrix.getCoefficientsOfResults(),
                    enteringVariableService.getEnteringVariableIndex(),
                    inversionService.getBasicVariablesInverse(), matrix);
            display.doubleValue("Value of leaving variable ", leavingVariableService.getMinimumValue());
            display.integerValue("Index of leaving variable ", leavingVariableService.getMinimumIndex());

            //Displace basic variables and non-basic variables
            DisplacementService displacementService = new DisplacementServiceImpl(matrix,
                    enteringVariableService.getEnteringVariableIndex(),
                    leavingVariableService.getMinimumIndex());
            turn++;
        } while (true);
    }
}
