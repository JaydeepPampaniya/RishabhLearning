package com.springai.Spring.Ai.service;

import com.springai.Spring.Ai.controller.request.ChatRequest;
import com.springai.Spring.Ai.controller.request.Message;
import com.springai.Spring.Ai.controller.response.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenRouterApiService {

    @Value("${openrouter.api.url}")
    private String apiUrl;

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.model}")
    private String modelName; // Inject model from config

    private final RestTemplate restTemplate = new RestTemplate();

    public String sendMessage(String message) {

        Message chatMessage = new Message("user", message);
        ChatRequest chatRequest = ChatRequest.builder()
                .model(modelName)
                .messages(List.of(chatMessage)).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.set("X-Title", "SpringAiApp");

        HttpEntity<ChatRequest> httpEntity = new HttpEntity<>(chatRequest, headers);

        try {
            ResponseEntity<ChatResponse> responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    httpEntity,
                    ChatResponse.class
            );

            ChatResponse chatResponse = responseEntity.getBody();

            if (chatResponse != null && chatResponse.getChoices() != null && !chatResponse.getChoices().isEmpty()) {
                return chatResponse.getChoices().get(0).getMessage().getContent();
            } else {
                throw new RuntimeException("Empty response or no choices returned by OpenRouter API");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("OpenRouter API error: " + e.getResponseBodyAsString(), e);
        }
    }
}
