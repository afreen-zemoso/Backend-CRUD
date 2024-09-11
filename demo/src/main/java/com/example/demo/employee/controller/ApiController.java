package com.example.demo.employee.controller;

import com.example.demo.employee.service.ExternalApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final ExternalApiService externalApiService;

    public ApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/fetch-data")
    public String fetchDataFromApi(@RequestParam String apiUrl) {
        return externalApiService.getExternalApiData(apiUrl);
    }
}

