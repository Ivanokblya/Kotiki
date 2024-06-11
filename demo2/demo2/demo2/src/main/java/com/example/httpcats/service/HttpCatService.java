package com.example.httpcats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class HttpCatService {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "catImages", key = "#statusCode", unless="#result == null")
    private ResponseEntity<byte[]> fetchCatImage(int statusCode) {
        String catUrl = "https://http.cat/" + statusCode;
        byte[] image = restTemplate.getForObject(catUrl, byte[].class);

        return ResponseEntity.status(HttpStatus.OK).body(image);
    }



}
