package app.api.controller;

import app.api.service.FileProcessingService;
import app.api.service.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/files")
public class Controller {

    private final FileProcessingService fileProcessingService;

    @Autowired
    public Controller(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @GetMapping("/txt")
    public String getFileTXT(@RequestParam(name = "url") String url) throws IOException {
        return fileProcessingService.processFile(url, FileType.TXT);
    }

    @GetMapping("/json")
    public String getFileJson(@RequestParam(name = "url") String url) {
        return fileProcessingService.processFile(url, FileType.JSON);
    }

    @GetMapping("/pdf")
    public String getFilePDF(@RequestParam(name = "url") String url) {
        return fileProcessingService.processFile(url, FileType.PDF); // Você pode ajustar conforme necessário
    }
}
