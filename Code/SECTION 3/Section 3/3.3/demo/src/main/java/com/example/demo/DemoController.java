package com.example.demo;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class DemoController {

    @RequestMapping("/")
    public String helloMessage() {
        return "Hello World!";
    }

}

