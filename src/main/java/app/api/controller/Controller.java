package app.api.controller;

import app.api.service.FileService;
import app.api.service.HttpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@RestController
@RequestMapping("api/files")
public class Controller {

    @Autowired
    private final FileService fileService;
    @Autowired
    private final HttpConnection httpConnection;

    public Controller(FileService fileService, HttpConnection httpConnection) { //injecao de dependencias
        this.fileService = fileService;
        this.httpConnection = httpConnection;
    }

    @GetMapping("/txt")
    public String getFileTXT() {
        try {
            fileService.createFileTxt();

            httpConnection.getBaseUrl("https://jsonplaceholder.typicode.com/todos");
            HttpURLConnection con = httpConnection.getConnection();

            StringBuilder response = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append(System.lineSeparator());
                }
            }

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error to request";
        }
    }
}
