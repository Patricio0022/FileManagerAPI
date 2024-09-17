package app.api.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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


            public String writeToFileTXT(String content) throws IOException {
        File newFile = new File("./files/file.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true))) {
            writer.write(content);
            writer.newLine();

        }
        return content;
    }

    public String writeToFileJson(String content) throws IOException {
        File newFile = new File("./files/fileJson.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true))) {
            writer.write(content);
            writer.newLine();

        }
        return content;
    }


    }