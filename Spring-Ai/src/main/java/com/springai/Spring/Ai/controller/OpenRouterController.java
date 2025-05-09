package com.springai.Spring.Ai.controller;

import com.springai.Spring.Ai.service.OpenRouterApiService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ai")
public class OpenRouterController {

    private final OpenRouterApiService openRouterApiService;
    private final VectorStore vectorStore;
    @Autowired
    public OpenRouterController(OpenRouterApiService openRouterApiService,VectorStore vectorStore) {
        this.openRouterApiService = openRouterApiService;
        this.vectorStore = vectorStore;
    }

    @PostMapping("/add-docs")
    public void addDocs() {
        openRouterApiService.addSampleDocuments();
    }

    // What is -> What is Spring boot and git and explain me in details, What is Git,

    @GetMapping("/search")
    public List<String> searchDocuments(@RequestParam("query") String query) {
        double threshold = query.trim().length() < 10 ? 0.3 : 0.7;

        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.query(query).withSimilarityThreshold(threshold).withTopK(5)
        );

        return results.stream()
                .map(Document::getContent)
                .collect(Collectors.toList());
    }

    @GetMapping("/ask")
    public ResponseEntity<String> askModel(@RequestParam String prompt) {
        String response = openRouterApiService.sendMessage(prompt);
        return ResponseEntity.ok(response);
    }
}
