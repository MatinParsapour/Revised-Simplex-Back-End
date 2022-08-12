package ir.algorithm.revisedSimplex.RevisedSimplex.service;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.File;

public interface FileService<T extends File> extends DataService<File> {

    void extract(T data);

}
