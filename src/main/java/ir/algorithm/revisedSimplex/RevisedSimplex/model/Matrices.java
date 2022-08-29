package ir.algorithm.revisedSimplex.RevisedSimplex.model;

import lombok.*;

import java.util.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matrices {

    private final List<Double> coefficientsOfTargetFunction = new LinkedList<>();
    private List<Double> basicVariablesInTargetFunction = new LinkedList<>();
    private List<Double> nonBasicVariablesInTargetFunction = new LinkedList<>();
    private final List<List<Double>> coefficientsOfConstraints = new LinkedList<>();
    private final List<Double> coefficientsOfResults = new LinkedList<>();
    private Map<Integer, List<Double>> basicVariables = new TreeMap<>();
    private Map<Integer, List<Double>> nonBasicVariables = new TreeMap<>();


}
