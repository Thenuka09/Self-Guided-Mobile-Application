package com.anxietyAPI.rest_controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anxiety")
public class StudentApiController {

    @GetMapping("/test")
    public String textEndPoint(){
        return "test endpoint is working";
    }
}
