package app.api.controller;

import app.api.service.FileProcessingService;
import app.api.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/files")
public class Controller {
    FileService fileService = new FileService();
    private final FileProcessingService fileProcessingService;

    @Autowired
    public Controller(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @GetMapping("/txt")
    public String getFileTXT(@RequestParam(name = "url") String url) throws IOException {
        fileService.createFileTxt();
        return fileProcessingService.processFile(url, true);

    }

    @GetMapping("/json")
    public String getFileJson(@RequestParam(name = "url") String url) {
        return fileProcessingService.processFile(url, false);
    }
}
