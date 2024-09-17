package app.api.service;

import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.*;

import java.io.*;

@Service
public class FileService {
    private String fileTXT;
    private String filePDF;
    private String fileJSON;
    private String fileCSV;

    private String content;

    public void createFileTxt() throws IOException {
        java.io.File dir = new java.io.File("./files");
        java.io.File fileTXT = new java.io.File(dir, "file.txt");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!fileTXT.exists()) {
            fileTXT.createNewFile();
        }
    }

    public void createFileJson() throws IOException {
        java.io.File dir = new java.io.File("./files");
        java.io.File dataJson = new java.io.File(dir, "dataJson");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!dataJson.exists()) {
            dataJson.createNewFile();
        }
    }


            public String writeToFile(String content) throws IOException {
        File newFile = new File("./files/file.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true))) {
            writer.write(content);
            writer.newLine();

        }
        return content;
    }


    }