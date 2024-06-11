package com.example.httpcats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class HttpCatController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getCatImage")
    public ResponseEntity<String> getCatImageForUrl(@RequestParam String url) {
        try {
            int statusCode = getStatusCode(url);
            return ResponseEntity.ok().body(String.valueOf(statusCode));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error getting status code");
        }
    }

    private int getStatusCode(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection.getResponseCode();
    }
}
