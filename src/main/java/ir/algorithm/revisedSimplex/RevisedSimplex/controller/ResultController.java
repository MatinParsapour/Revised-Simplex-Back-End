package ir.algorithm.revisedSimplex.RevisedSimplex.controller;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/result")
@RestController
public class ResultController {

    @GetMapping("/get-attempts")
    public ResponseEntity getAttempts() {
        return ResponseEntity.status(HttpStatus.OK).body(Result.getAttempts());
    }
    @GetMapping("/get-optimal-result")
    public ResponseEntity getOptimalResult() {
        return ResponseEntity.status(HttpStatus.OK).body(Result.getOptimalResult());
    }
    @GetMapping("/get-basic-variables")
    public ResponseEntity getBasicVariables() {
        return ResponseEntity.status(HttpStatus.OK).body(Result.getBasicVariables());
    }
    @GetMapping("/get-non-basic-variables")
    public ResponseEntity getNonBasicVariables() {
        return ResponseEntity.status(HttpStatus.OK).body(Result.getNonBasicVariables());
    }
    @GetMapping("/get-optimal-values")
    public ResponseEntity getOptimalValues() {
        return ResponseEntity.status(HttpStatus.OK).body(Result.getOptimalValues());
    }
}
