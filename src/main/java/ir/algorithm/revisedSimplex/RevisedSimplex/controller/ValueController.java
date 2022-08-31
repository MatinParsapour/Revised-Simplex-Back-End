package ir.algorithm.revisedSimplex.RevisedSimplex.controller;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Data;
import ir.algorithm.revisedSimplex.RevisedSimplex.model.Matrices;
import ir.algorithm.revisedSimplex.RevisedSimplex.model.MatricesValues;
import ir.algorithm.revisedSimplex.RevisedSimplex.model.Value;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/value")
public class ValueController {

    private final ValueService<MatricesValues> valueService;

    @Autowired
    public ValueController(ValueService<MatricesValues> valueService) {
        this.valueService = valueService;
    }

    @PostMapping("/send-data")
    public void sendDate(@RequestBody MatricesValues values){
        valueService.extract(values);
    }

}
