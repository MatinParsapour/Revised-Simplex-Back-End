package ir.algorithm.revisedSimplex.RevisedSimplex.service.impl;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.ExcelFile;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.ExcelFileService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;

@Service
public class ExcelFileServiceImpl implements ExcelFileService {

    @Override
    public void extract(ExcelFile data) {
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(data.getData().getInputStream());
        } catch (IOException exception) {
            System.out.println(exception.getCause() + " " + exception.getMessage());
        }
        assert workbook != null;
        XSSFSheet worksheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = worksheet.rowIterator();
        Double sum = 0.0;
        while (rowIterator.hasNext()) {
            for (Cell cell : rowIterator.next()) {
                try {
                    String stringCellValue = cell.getStringCellValue();
                    if (stringCellValue.equalsIgnoreCase("max z =")) {
                        System.out.println("target function");
                    } else if (stringCellValue.equals("st.")) {
                        System.out.println("Constraints");
                    } else if (stringCellValue.equals("results")) {
                        System.out.println("Result matrix");
                    }
                } catch (IllegalStateException exception) {
                    System.out.println(sum);
                    sum += cell.getNumericCellValue();
                    System.out.println(cell);
                }

            }
        }
        System.out.println(sum);
        Iterator<Row> iterator = worksheet.iterator();
        while (iterator.hasNext()) {
            for (Cell cell: iterator.next()) {
                System.out.println(cell);
            }
        }
    }
}
