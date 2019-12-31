package com.ensa.projet.controllers;

import com.ensa.projet.models.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TasksController {
    @GetMapping("/hello")
    public Task hello(){
         return new Task();
    }
}
