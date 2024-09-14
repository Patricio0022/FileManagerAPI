package app.api;

import app.api.service.HttpConnection;

import java.io.*;
import java.net.HttpURLConnection;

public class Main {
    public static void main(String[] args) throws IOException {

        File dir = new File("./files");
        File file = new File(dir, "json.json");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        HttpConnection httpConnection = new HttpConnection();
        httpConnection.getBaseUrl("https://jsonplaceholder.typicode.com/todos");
        HttpURLConnection con = httpConnection.getConnection();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
             FileWriter fileWriter = new FileWriter(file)) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line).append(System.lineSeparator());
            }

            fileWriter.write(response.toString());
        }

        System.out.println(httpConnection.getStatusCode());
    }
}
