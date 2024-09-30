package app.api.controller;

import app.api.service.FileProcessingService;
import app.api.service.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/file", produces = {
            MediaType.TEXT_PLAIN_VALUE,
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_PDF_VALUE,
            "text/csv"
    })
    public ResponseEntity<?> getFile(@RequestParam(name = "url") String url,
                                     @RequestHeader(value = "Accept", required = false)
        String acceptHeader) {
        String response;
        try {

            if (acceptHeader != null) {
                if (acceptHeader.contains(MediaType.APPLICATION_PDF_VALUE)) {

                    response = fileProcessingService.processFile(url, FileType.PDF);
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(response);

                } else if (acceptHeader.contains("text/csv")) {

                    response = fileProcessingService.processFile(url, FileType.CSV);
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType("text/csv"))
                            .body(response);

                } else if (acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE)) {

                    response = fileProcessingService.processFile(url, FileType.JSON);
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(response);

                } else if (acceptHeader.contains(MediaType.TEXT_PLAIN_VALUE)) {

                    response = fileProcessingService.processFile(url, FileType.TXT);
                    return ResponseEntity.ok()
                            .contentType(MediaType.TEXT_PLAIN)
                            .body(response);
                } else {

                    return ResponseEntity.status(415).body("Unsupported media type requested.");
                }
            } else {

                response = fileProcessingService.processFile(url, FileType.JSON);
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response);
            }
        } catch (IOException e) {

            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }
}
