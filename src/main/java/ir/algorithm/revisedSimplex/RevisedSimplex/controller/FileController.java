package ir.algorithm.revisedSimplex.RevisedSimplex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

    @PostMapping("/import-excel")
    public void importExcelFile(@RequestParam("file")MultipartFile file) {

    }
}
