package app.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {
    private File fileTXT;
    private File dir;
    private String filePDF;
    private File fileJSON;
    private String fileCSV;

    private String content;

    public void createFileTxt() throws IOException {
        dir = new File("./files");
        fileTXT = new File(dir, "file.txt");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!fileTXT.exists()) {
            fileTXT.createNewFile();
        }
    }

    public void createFileJson() throws IOException {
        dir = new File("./files");
        fileJSON = new File(dir, "data.json");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!fileJSON.exists()) {
            fileJSON.createNewFile();
        }
    }

    public void writeFileJSON(String jsonData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileJSON, jsonNode);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar dataJson", e);
        }
    }

    public String writeFileTXT(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTXT, true))) {
            writer.write(content);
            writer.newLine();
        }
        return content;
    }


    }