package com.springai.Spring.Ai.controller.request;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequest {
    private String model;
    private List<Message> messages;
}

