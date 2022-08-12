package ir.algorithm.revisedSimplex.RevisedSimplex.service;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Data;

public interface DataService<E extends Data> {

    void extract(E data);

}
