package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.ExcelFile;
import ir.algorithm.revisedSimplex.RevisedSimplex.model.Matrices;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.CalculationService;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.ExcelFileService;
import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ExcelFileServiceImpl implements ExcelFileService {

    private Matrices matrix;
    private static XSSFWorkbook workbook = null;

    @Override
    public void extract(ExcelFile data) {
        try {
            workbook = new XSSFWorkbook(data.getData().getInputStream());
        } catch (IOException exception) {
            System.out.println(exception.getCause() + " " + exception.getMessage());
        }
        if (workbook == null) {
            throw new EmptyFileException();
        }
        this.matrix = new Matrices();
        iteratorThroughWorksheets();
        startCalculating();
    }

    private void iteratorThroughWorksheets() {
        for (int i = 0; i <= 4; i++) {
            String worksheetName = workbook.getSheetAt(i).getSheetName();
            if (worksheetName.equalsIgnoreCase("target function")) {
                getTargetFunctionValues();
            } else if (worksheetName.equalsIgnoreCase("constraints")) {
                getConstraintsValues();
            } else if (worksheetName.equalsIgnoreCase("basic variables")) {
                getBasicVariablesValues();
            } else if (worksheetName.equalsIgnoreCase("non basic variables")) {
                getNonBasicVariablesValues();
            } else if (worksheetName.equalsIgnoreCase("results")) {
                getResultValues();
            } else {
                throw new IllegalArgumentException("Name of sheet is wrong, please select a correct name");
            }
        }
    }

    private void getTargetFunctionValues() {
        XSSFSheet targetFunctionWorksheet = workbook.getSheetAt(0);
        Iterator<Row> targetFunctionWorksheetRowIterator = targetFunctionWorksheet.rowIterator();
        for (Cell cell : targetFunctionWorksheetRowIterator.next()) {
            matrix.getCoefficientsOfTargetFunction().add(cell.getNumericCellValue());
        }
    }

    private void getConstraintsValues() {
        XSSFSheet constraintsWorksheet = workbook.getSheetAt(1);
        Iterator<Row> constraintsWorksheetRowIterator = constraintsWorksheet.rowIterator();
        while (constraintsWorksheetRowIterator.hasNext()) {
            List<Double> rowVariablesValues = new LinkedList<>();
            for (Cell cell : constraintsWorksheetRowIterator.next()) {
                rowVariablesValues.add(cell.getNumericCellValue());
            }
            matrix.getCoefficientsOfConstraints().add(rowVariablesValues);
        }
    }

    private void getBasicVariablesValues() {
        getBasicVariablesInTargetFunction();
        getBasicVariablesInConstraints();
    }

    private void getBasicVariablesInTargetFunction(){
        XSSFSheet basicVariablesWorksheet = workbook.getSheetAt(2);
        Iterator<Row> basicVariablesWorksheetRowIterator = basicVariablesWorksheet.rowIterator();
        for (Cell cell : basicVariablesWorksheetRowIterator.next()) {
            int columnOfBasicVariable = (int) cell.getNumericCellValue();
            double basicVariable = matrix.getCoefficientsOfTargetFunction().get(columnOfBasicVariable - 1);
            matrix.getBasicVariablesInTargetFunction().add(basicVariable);
        }
    }

    private void getBasicVariablesInConstraints(){
        XSSFSheet basicVariablesWorksheet = workbook.getSheetAt(2);
        Iterator<Row> basicVariablesWorksheetRowIterator = basicVariablesWorksheet.rowIterator();
        int numberOfConstraints = matrix.getCoefficientsOfConstraints().size();
        for (Cell cell : basicVariablesWorksheetRowIterator.next()) {
            int columnOfBasicVariable = (int) cell.getNumericCellValue();
            List<Double> basicVariables = new LinkedList<>();
            for (int row = 0; row < numberOfConstraints; row++) {
                double basicVariable = matrix.getCoefficientsOfConstraints().get(row).get(columnOfBasicVariable - 1);
                basicVariables.add(basicVariable);
            }
            matrix.getBasicVariables().put(columnOfBasicVariable - 1, basicVariables);
        }
    }

    private void getNonBasicVariablesValues() {
        getNonBasicVariablesInTargetFunction();
        getNonBasicVariablesInConstraints();
    }

    private void getNonBasicVariablesInTargetFunction(){
        XSSFSheet basicVariablesWorksheet = workbook.getSheetAt(3);
        Iterator<Row> basicVariablesWorksheetRowIterator = basicVariablesWorksheet.rowIterator();
        for (Cell cell : basicVariablesWorksheetRowIterator.next()) {
            int columnOfNonBasicVariable = (int) cell.getNumericCellValue();
            double nonBasicVariable = matrix.getCoefficientsOfTargetFunction().get(columnOfNonBasicVariable - 1);
            matrix.getNonBasicVariablesInTargetFunction().add(nonBasicVariable);
        }
    }

    private void getNonBasicVariablesInConstraints(){
        XSSFSheet basicVariablesWorksheet = workbook.getSheetAt(3);
        Iterator<Row> basicVariablesWorksheetRowIterator = basicVariablesWorksheet.rowIterator();
        int numberOfConstraints = matrix.getCoefficientsOfConstraints().size();
        for (Cell cell : basicVariablesWorksheetRowIterator.next()) {
            int columnOfNonBasicVariable = (int) cell.getNumericCellValue();
            List<Double> nonBasicVariables = new LinkedList<>();
            for (int row = 0; row < numberOfConstraints; row++) {
                double nonBasicVariable = matrix.getCoefficientsOfConstraints().get(row).get(columnOfNonBasicVariable - 1);
                nonBasicVariables.add(nonBasicVariable);
            }
            matrix.getNonBasicVariables().put(columnOfNonBasicVariable - 1, nonBasicVariables);
        }
    }

    private void getResultValues() {
        XSSFSheet resultWorkSheet = workbook.getSheetAt(4);
        Iterator<Row> resultWorksheetRowIterator = resultWorkSheet.rowIterator();
        for (Cell cell : resultWorksheetRowIterator.next()) {
            double resultVariableValue = cell.getNumericCellValue();
            matrix.getCoefficientsOfResults().add(resultVariableValue);
        }
    }

    private void startCalculating(){
        CalculationService calculationService = new CalculationServiceImpl(matrix);
    }
}
