package com.andres_play.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.andres_play.domain.service.AndresStreamAiService;

@RestController
public class HelloController {
    private final String plataform;
    private final AndresStreamAiService aiService;

    public HelloController(@Value("${spring.application.name}") String plataform, AndresStreamAiService aiService) {
        this.plataform = plataform;
        this.aiService = aiService;
    }

    @GetMapping("/")
    public String hello(){
        return this.aiService.generateGreetings(plataform);
    }

}
