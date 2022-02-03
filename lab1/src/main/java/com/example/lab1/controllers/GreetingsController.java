package com.example.lab1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class GreetingsController {
    @GetMapping
    public String htmlFileName(){
        File f = new File("C:\\Users\\Ayana\\Documents\\university\\spring_labs\\lab1\\src\\main\\resources\\" +
                "templates\\welcome.html");
        return f.getName();
    }
}
