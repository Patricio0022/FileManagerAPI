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
        java.io.File file = new java.io.File(dir, "file.txt");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
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