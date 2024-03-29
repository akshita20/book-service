package com.practice.bookservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SpringClientTestController {

    @Value("${msg:Hello World from book-service}")
    private String msg;

    @GetMapping("/client-config")
    public String getBooks() {
        return msg;
    }
}
