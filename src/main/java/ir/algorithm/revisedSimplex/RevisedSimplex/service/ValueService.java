package ir.algorithm.revisedSimplex.RevisedSimplex.service;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.MatricesValues;
import ir.algorithm.revisedSimplex.RevisedSimplex.model.Value;

public interface ValueService<T extends MatricesValues> extends DataService<Value> {

    void extract(T data);
}
