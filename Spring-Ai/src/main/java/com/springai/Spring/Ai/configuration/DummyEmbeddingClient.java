package com.springai.Spring.Ai.configuration;

import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.document.Document;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DummyEmbeddingClient implements EmbeddingClient {

    private static final String API_URL = "http://localhost:5005/embed";
    private static final int VECTOR_DIM = 384;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Double> embed(String text) {
        return embed(List.of(text)).getFirst();
    }

    @Override
    public List<Double> embed(Document document) {
        return embed(document.getContent());
    }

    @Override
    public List<List<Double>> embed(List<String> texts) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = Map.of("inputs", texts);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<List> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, List.class);
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("Failed to get embeddings: " + response.getStatusCode());
        }
        return parseEmbeddings(response.getBody());
    }

    private List<List<Double>> parseEmbeddings(List<?> rawEmbeddings) {
        return rawEmbeddings.stream()
                .map(item -> ((List<?>) item).stream()
                        .map(val -> ((Number) val).doubleValue())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Override
    public EmbeddingResponse embedForResponse(List<String> texts) {
        return new EmbeddingResponse(embed(texts).stream()
                .map(vec -> new Embedding(vec, 0))
                .collect(Collectors.toList()));
    }

    @Override
    public int dimensions() {
        return VECTOR_DIM;
    }

    @Override
    public EmbeddingResponse call(EmbeddingRequest request) {
        return embedForResponse(request.getInstructions());
    }
}
