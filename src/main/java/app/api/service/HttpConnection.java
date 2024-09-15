package app.api.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class HttpConnection implements Url {
    private HttpURLConnection con = null;

    @Override
    public String getBaseUrl(String Url) {

        try {
           URL url = new URL(Url); //inicializando
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

        } catch (IOException e){
            System.out.println("Error to send http request" + e.getMessage());
            e.printStackTrace();
        }

        return Url;
    }

    @Override
    public String getStatusCode () throws IOException{
        return String.valueOf(con.getResponseCode());
    }

    public HttpURLConnection getConnection() {
        return con;
    }
}
