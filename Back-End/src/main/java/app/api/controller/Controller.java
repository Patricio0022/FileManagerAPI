package app.api.controller;

import app.api.service.FileService;
import app.api.service.HttpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/files")
public class Controller {

    private final FileService fileService; // Declarations
    private final HttpConnection httpConnection;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public Controller(FileService fileService, HttpConnection httpConnection) {
        this.fileService = fileService;
        this.httpConnection = httpConnection;
    }

    @GetMapping("/txt")
    public String getFileTXT(@RequestParam(name = "url") String url) { // Try-with-resources
        try {
            fileService.createFileTxt();

            fileService.createFileJson(); //mockar

            httpConnection.setBaseUrl(url);
            HttpURLConnection con = httpConnection.getConnection();
            String statuscode = httpConnection.getStatusCode();

            if (Objects.equals(statuscode, String.valueOf(HttpURLConnection.HTTP_OK))) {
                logger.info("Request successful: Status code {}", statuscode);
            } else {
                logger.error("Fail to request: Status code {}", statuscode);
                return "Failed to request with status code: " + statuscode;
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append(System.lineSeparator());
                }
            }

            String responseBody = response.toString().trim();
            if (responseBody.startsWith("{") || responseBody.startsWith("[")) {
                logger.info("Response is JSON, OK continue");

                Map<String, String> removeChacaracteres = new HashMap<>();
                removeChacaracteres.put("[", "");
                removeChacaracteres.put("]", "");
                removeChacaracteres.put("{", "");
                removeChacaracteres.put("}", "");

                responseBody = responseBody.replaceAll("\\s+", "");

                for (Map.Entry<String, String> entry : removeChacaracteres.entrySet()) {
                    responseBody = responseBody.replace(entry.getKey(), entry.getValue());
                }




                fileService.writeToFileTXT(responseBody);
            } else {
                logger.warn("Response is not JSON.");
            }

            return responseBody;

        } catch (IOException e) {
            logger.error("Error processing request", e);
            return "Error processing request";
        }
    }

        @GetMapping("/json")
        public String getFileJson(@RequestParam(name = "url") String url) { // Try-with-resources
            try {
                fileService.createFileJson(); //mockar

                httpConnection.setBaseUrl(url);
                HttpURLConnection con = httpConnection.getConnection();
                String statuscode = httpConnection.getStatusCode();

                if (Objects.equals(statuscode, String.valueOf(HttpURLConnection.HTTP_OK))) {
                    logger.info("Request successful: Status code {}", statuscode);
                } else {
                    return "Failed to request with status code: " + statuscode;
                }

                StringBuilder response = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append(System.lineSeparator());
                    }
                }

                String responseBody = response.toString().trim();
                if (responseBody.startsWith("{") || responseBody.startsWith("[")) {
                    logger.info("Response is JSON, OK continue");

                    fileService.writeToFileJson(responseBody);

                } else {
                    logger.warn("Response is not JSON.");
                }

                return responseBody;

            } catch (IOException e) {
                logger.error("Error processing request", e);
                return "Error processing request";
            }
    }
}
