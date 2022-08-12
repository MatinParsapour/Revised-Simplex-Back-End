package ir.algorithm.revisedSimplex.RevisedSimplex.service;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Value;

public interface ValueService<T extends Value> extends DataService<Value> {

    void extract(T data);
}
