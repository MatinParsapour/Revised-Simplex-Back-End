package ir.algorithm.revisedSimplex.RevisedSimplex.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatricesValues {

    private List<Double> targetFunctionValues;

    private List<List<Double>> constraintsValues;

    private List<Double> resultValues;

    private List<Integer> basicVariablesColumns;

    private List<Integer> nonBasicVariablesColumns;
}
