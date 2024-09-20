package app.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.HttpURLConnection;


@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException { //Exception moved

        SpringApplication.run(Main.class, args);
        System.out.println("Listening ...");

    }
}
