package app.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
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

    public String processFile(String url, FileType fileType) throws IOException {

        httpConnection.setBaseUrl(url);
        HttpURLConnection connection = httpConnection.getConnection();
        String statusCode = httpConnection.getStatusCode();


        if (!Objects.equals(statusCode, String.valueOf(HttpURLConnection.HTTP_OK))) {
            logger.error("fail to request {}", statusCode);
            return "status code: " + statusCode;
        }


        String responseBody = httpConnection.readResponse(connection);

        if (responseBody.startsWith("{") || responseBody.startsWith("[")) {
            logger.info("is json, continue process");


            switch (fileType) {
                case TXT:
                    fileService.writeToFileTXT(responseBody);
                    return responseBody;

                case PDF:
                    return handlePdfFile();

                case JSON:
                    fileService.writeToFileJson(responseBody);
                    return responseBody;

                case CSV:
                    return handleCsvFile(responseBody);

                default:
                    logger.warn("file not supported: {}", fileType);
                    return "file not supported";
            }
        } else {
            logger.warn("the response is not valid json");
            return "not is json";
        }
    }

    private String handlePdfFile() throws IOException {
        String inputTxtFile = "./files/file.txt";
        String outputPdfFile = "./files/output.pdf";


        if (new File(inputTxtFile).exists()) {
            fileService.writeToFilePdf(inputTxtFile, outputPdfFile);
            return "file not generated ";
        } else {
            logger.error("the file input not exists {}", inputTxtFile);
            return "the file input not exists";
        }
    }

    private String handleCsvFile(String responseBody) throws IOException {
        fileService.writeToFileCsv(responseBody);
        return "file generated!";
    }
}
