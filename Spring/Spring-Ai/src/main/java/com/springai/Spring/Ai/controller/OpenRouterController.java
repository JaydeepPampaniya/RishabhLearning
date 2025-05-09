package com.springai.Spring.Ai.controller;

import com.springai.Spring.Ai.controller.request.ChatRequest;
import com.springai.Spring.Ai.service.OpenRouterApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class OpenRouterController {

    private final OpenRouterApiService openRouterApiService;

    @Autowired
    public OpenRouterController(OpenRouterApiService openRouterApiService) {
        this.openRouterApiService = openRouterApiService;
    }

    @PostMapping("/ask")
    public String askModel(@RequestParam String prompt) {
        return openRouterApiService.sendMessage(prompt);
    }
}
