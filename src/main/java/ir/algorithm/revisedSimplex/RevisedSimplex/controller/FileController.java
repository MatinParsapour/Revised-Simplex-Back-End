package ir.algorithm.revisedSimplex.RevisedSimplex.controller;

import ir.algorithm.revisedSimplex.RevisedSimplex.service.ExcelFileService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping("/file")
public class FileController {

    private final ExcelFileService excelFileService;

    @Autowired
    public FileController(ExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

    @PostMapping("/import-excel")
    public void importExcelFile(@RequestParam("file")MultipartFile file) {

    }
}
