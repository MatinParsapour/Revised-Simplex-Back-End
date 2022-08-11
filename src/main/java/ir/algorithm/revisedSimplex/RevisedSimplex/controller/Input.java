package ir.algorithm.revisedSimplex.RevisedSimplex.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@Controller
@RequestMapping("/input")
public class Input {

    @PostMapping("/send-file")
    public void getExcelFile(@RequestParam("file")MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = worksheet.rowIterator();
        while (rowIterator.hasNext()) {
            for (Cell cell : rowIterator.next()) {
                System.out.println(cell);
            }
        }
        Iterator<Row> iterator = worksheet.iterator();
        while (iterator.hasNext()) {
            for (Cell cell: iterator.next()) {
                System.out.println(cell);
            }
        }
    }
}
