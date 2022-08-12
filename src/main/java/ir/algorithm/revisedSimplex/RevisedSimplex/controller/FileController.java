package ir.algorithm.revisedSimplex.RevisedSimplex.controller;

import ir.algorithm.revisedSimplex.RevisedSimplex.model.ExcelFile;
import ir.algorithm.revisedSimplex.RevisedSimplex.service.ExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/file")
public class FileController {

    private final ExcelFileService excelFileService;

    @Autowired
    public FileController(ExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

    @PostMapping("/import-excel")
    public void importExcelFile(@RequestParam("file")MultipartFile file) throws IOException {
        ExcelFile newExcelFile = ExcelFile.builder().build();
        newExcelFile.setData(file);
        excelFileService.extract(newExcelFile);
    }
}
