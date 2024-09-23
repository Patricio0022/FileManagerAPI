package app.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FileProcessingService {

    private final FileService fileService;
    private final HttpConnectionService httpConnection;
    private static final Logger logger = LoggerFactory.getLogger(FileProcessingService.class);

    @Autowired
    public FileProcessingService(FileService fileService, HttpConnectionService httpConnection) {
        this.fileService = fileService;
        this.httpConnection = httpConnection;
    }

    public String processFile(String url, FileType fileType) {
        try {
            httpConnection.setBaseUrl(url);
            HttpURLConnection connection = httpConnection.getConnection();
            String statusCode = httpConnection.getStatusCode();

            if (!Objects.equals(statusCode, String.valueOf(HttpURLConnection.HTTP_OK))) {
                logger.error("Failed to request: Status code {}", statusCode);
                return "Failed to request with status code: " + statusCode;
            }

            String responseBody = httpConnection.readResponse(connection);

            if (responseBody.startsWith("{") || responseBody.startsWith("[")) {
                logger.info("Response is JSON, OK to continue");


                if (fileType == FileType.TXT) {
                    fileService.writeToFileTXT(responseBody);
                    responseBody = cleanResponse(responseBody);

                } else if (fileType == FileType.PDF) {
                    String inputTxtFile = "./files/file.txt";
                    String outputPdfFile = "./files/output.pdf";


                    if (new File(inputTxtFile).exists()) {
                        fileService.writeToFilePdf(inputTxtFile, outputPdfFile);
                    } else {
                        logger.error("Input TXT file does not exist: {}", inputTxtFile);
                        return "Input TXT file does not exist.";
                    }
                } else {
                    fileService.writeToFileJson(responseBody);
                }

                return responseBody;
            } else {
                logger.warn("Response is not JSON.");
                return "Response is not JSON";
            }

        } catch (IOException e) {
            logger.error("Error processing request", e);
            return "Error processing request";
        }
    }

    private String cleanResponse(String responseBody) {
        Map<String, String> removeCharacters = new HashMap<>();
        removeCharacters.put("[", "");
        removeCharacters.put("]", "");
        removeCharacters.put("{", "");
        removeCharacters.put("}", "");

        responseBody = responseBody.replaceAll("\\s+", "");

        for (Map.Entry<String, String> entry : removeCharacters.entrySet()) {
            responseBody = responseBody.replace(entry.getKey(), entry.getValue());
        }

        return responseBody;
    }
}
