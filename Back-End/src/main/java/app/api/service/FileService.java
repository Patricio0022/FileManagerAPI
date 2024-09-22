package app.api.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.springframework.stereotype.Service;

import java.io.*;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA;

@Service
public class FileService {
    private static PDFont Standard14Fonts;
    private File fileTXT;
    private File dir;
    private File filePDF;
    private String fileJSON;
    private String fileCSV;

    private String content;

    public void createFileTxt() throws IOException {
         dir = new java.io.File("./files");
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
        fileTXT = new File("./files/file.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTXT, true))) {
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

    public static void writeToFilePdf(String inputTxtFile, String outputPdfFile) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont( Standard14Fonts, 12);
        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 700);

        try (BufferedReader br = new BufferedReader(new FileReader(inputTxtFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentStream.showText(line);
                contentStream.newLine();
            }
        }

        contentStream.endText();
        contentStream.close();
        document.save(outputPdfFile);
        document.close();

    }

    }