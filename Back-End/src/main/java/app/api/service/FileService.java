package app.api.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.*;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA;

@Service
public class FileService {

    private static final String BASE_DIR = "./files";
    private static final String TXT_FILE_PATH = BASE_DIR + "/file.txt";
    private static final String JSON_FILE_PATH = BASE_DIR + "/fileJson.json";
    private static final String CSV_FILE_PATH = BASE_DIR + "/file.csv";


    public String writeToFileTXT(String content) throws IOException {
        createDirIfNotExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TXT_FILE_PATH, true))) {
            writer.write(content);
            writer.newLine();
        }
        return content;
    }

    public String writeToFileJson(String content) throws IOException {
        createDirIfNotExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_PATH, true))) {
            writer.write(content);
            writer.newLine();
        }
        return content;
    }


    public String writeToFileCsv(String content) throws IOException {
        createDirIfNotExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.write(content);
            writer.newLine();
        }
        return content;
    }

    public String writeToFilePdf(String inputTxtFile, String outputPdfFile) throws IOException {

        PDDocument document = new PDDocument();
        float yPosition = 700;
        float margin = 25;


        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);


        contentStream.setFont(new PDType1Font(HELVETICA), 12);
        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, yPosition);

        try (BufferedReader br = new BufferedReader(new FileReader(inputTxtFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                contentStream.showText(line);
                contentStream.newLine();

                yPosition -= 14.5f;


                if (yPosition < margin) {
                    contentStream.endText();
                    contentStream.close();


                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);

                    contentStream.setFont(new PDType1Font(HELVETICA), 12);
                    contentStream.beginText();
                    contentStream.setLeading(14.5f);
                    contentStream.newLineAtOffset(25, 700);

                    yPosition = 700;
                }
            }
        }


        contentStream.endText();
        contentStream.close();

        document.save(outputPdfFile);
        document.close();
        return "PDF created successfully.";
    }




    private void createDirIfNotExists() {
        File dir = new File(BASE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
