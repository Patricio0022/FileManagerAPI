package app.api.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileService {
    private String fileTXT;
    private String filePDF;
    private String fileJSON;
    private String fileCSV;

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
}