package app.api.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HttpConnectionService implements Url {
    private HttpURLConnection con;

    @Override
    public String setBaseUrl(String url) {
        try {
            URL urlObject = new URL(url);
            con = (HttpURLConnection) urlObject.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            System.out.println("Error to send http request: " + e.getMessage());
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public String getStatusCode() throws IOException {
        if (con != null) {
            return String.valueOf(con.getResponseCode());
        }
        return "No connection available";
    }

    public HttpURLConnection getConnection() {
        return con;
    }

    public String readResponse(HttpURLConnection con) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line).append(System.lineSeparator());
            }
        }
        return response.toString().trim();
    }
}
