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

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/files")
public class Controller {

    private final FileService fileService;
    private final HttpConnection httpConnection;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public Controller(FileService fileService, HttpConnection httpConnection) {
        this.fileService = fileService;
        this.httpConnection = httpConnection;
    }

    @GetMapping("/txt")
    public String getFileTXT(@RequestParam(name = "url") String url) {
        try {
            fileService.createFileTxt();

            httpConnection.setBaseUrl(url);
            HttpURLConnection con = httpConnection.getConnection();
            int statuscode = con.getResponseCode();
            if(statuscode != HttpURLConnection.HTTP_OK){
                System.out.println("fail to request");
            } else{
                System.out.println(statuscode);
            }

            StringBuilder response = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append(System.lineSeparator());
                    logger.info("{}", line);
                    fileService.writeToFile(line);

                }

            }

            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing request";
        }
    }
}
