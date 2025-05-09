package com.springai.Spring.Ai.service;


import com.springai.Spring.Ai.controller.request.ChatRequest;
import com.springai.Spring.Ai.controller.request.Message;
import com.springai.Spring.Ai.controller.response.ChatResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ai.document.Document;

import java.util.List;

@Service
public class OpenRouterApiService {

    @Value("${openrouter.api.url}")
    private String apiUrl;

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.model}")
    private String modelName;

    private final RestTemplate restTemplate = new RestTemplate();
    private final VectorStore vectorStore;

    @Autowired
    public OpenRouterApiService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }
//Chatclient.call(messages);
    public String sendMessage(String message) {
        //app -> openrouter -> deepseek model -> openrouter -> app

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

    // Optional utility to add documents to vector DB
    public void addSampleDocuments() {
        List<Document> documents = List.of(

                new Document("What is Spring Boot?"),
                new Document("What is the benefit of using Spring Boot over traditional Java frameworks?"),
                new Document("What is the internal working of Spring Boot’s auto-configuration mechanism in Java?"),

                new Document("What is a REST API?"),
                new Document("What is the structure of a RESTful service and why is it important?"),
                new Document("What is the HTTP lifecycle in a REST API and how is it handled in Spring Boot?"),

                new Document("What is machine learning?"),
                new Document("What is the difference between supervised and unsupervised learning?"),
                new Document("What is the training and evaluation pipeline for a machine learning model?"),

                new Document("What is a neural network?"),
                new Document("What is the difference between a neural network and a traditional algorithm?"),
                new Document("What is backpropagation in neural networks and how does it work?"),

                new Document("What is DevOps?"),
                new Document("What is CI/CD and how does it fit into DevOps?"),
                new Document("What is GitOps and how is it implemented in modern DevOps pipelines?"),

                new Document("What is Docker?"),
                new Document("What is the difference between Docker and a Virtual Machine?"),
                new Document("What is Docker Compose and how does it simplify multi-container apps?"),

                new Document("What is Kubernetes?"),
                new Document("What is the architecture of Kubernetes and its core components?"),
                new Document("What is a Kubernetes operator and how is it used to automate CRDs?"),

                new Document("What is microservices architecture?"),
                new Document("What is the benefit of using microservices over monoliths?"),
                new Document("What is the role of service discovery in microservice communication?"),

                new Document("What is OAuth 2.0?"),
                new Document("What is the difference between OAuth 2.0 and OpenID Connect?"),
                new Document("What is the OAuth 2.0 authorization code flow and how is it secured?"),

                new Document("What is Java Virtual Machine (JVM)?"),
                new Document("What is the memory structure inside the JVM?"),
                new Document("What is the process of garbage collection in the JVM?"),

                new Document("What is GraphQL?"),
                new Document("What is the difference between GraphQL and REST?"),
                new Document("What is GraphQL schema stitching and how does it improve APIs?"),

                new Document("What is an API Gateway?"),
                new Document("What is the difference between an API Gateway and a Load Balancer?"),
                new Document("What is API rate limiting and how is it enforced at the gateway level?"),

                new Document("What is OpenAI’s GPT model?"),
                new Document("What is the difference between GPT-3 and GPT-4?"),
                new Document("What is the transformer architecture behind GPT models?"),

                new Document("What is an embedding model?"),
                new Document("What is the difference between static and contextual embeddings?"),
                new Document("What is cosine similarity and how is it used with embeddings?"),

                new Document("What is cloud computing?"),
                new Document("What is the difference between IaaS, PaaS, and SaaS?"),
                new Document("What is cloud-native development and why is it important?"),

                new Document("What is a load balancer?"),
                new Document("What is the difference between L4 and L7 load balancers?"),
                new Document("What is sticky session handling and why is it useful?"),

                new Document("What is continuous integration?"),
                new Document("What is the importance of automated testing in CI?"),
                new Document("What is Jenkins pipeline syntax and how is it configured?"),

                new Document("What is a container?"),
                new Document("What is the difference between a container and an image?"),
                new Document("What is container orchestration and how does Kubernetes handle it?"),

                new Document("What is Git?"),
                new Document("What is the difference between rebase and merge?"),
                new Document("What is Git internals: refs, objects, and packfiles?"),

                new Document("What is natural language processing?"),
                new Document("What is tokenization and part-of-speech tagging?"),
                new Document("What is named entity recognition and how is it trained?"),

                new Document("What are microservices?"),
                new Document("What are the benefits of microservices over monoliths?"),
                new Document("What are the communication patterns and challenges in microservice architectures?"),

                new Document("What are RESTful APIs?"),
                new Document("What are the key principles that define RESTful API design?"),
                new Document("What are the best practices for versioning and securing RESTful APIs?"),

                new Document("What are containers?"),
                new Document("What are the advantages of using containers in CI/CD pipelines?"),
                new Document("What are the internal layers of a Docker image and how do they work?"),

                new Document("What are the differences between TCP and UDP?"),
                new Document("What are the use cases for TCP vs UDP in modern networks?"),
                new Document("What are the performance impacts of TCP handshake and congestion control?"),

                new Document("What are access tokens and refresh tokens?"),
                new Document("What are the risks of long-lived tokens and how are they mitigated?"),
                new Document("What are the token rotation mechanisms in OAuth 2.0 flows?"),

                new Document("What are software design patterns?"),
                new Document("What are the differences between structural and behavioral patterns?"),
                new Document("What are real-world examples of applying design patterns in enterprise systems?"),

                new Document("What are machine learning pipelines?"),
                new Document("What are the steps from data preprocessing to model deployment?"),
                new Document("What are orchestration tools like Kubeflow or Airflow used for in ML workflows?"),

                new Document("What are cloud-native applications?"),
                new Document("What are the characteristics of twelve-factor applications?"),
                new Document("What are the best practices for building resilient cloud-native services?"),

                new Document("What are logs and metrics in observability?"),
                new Document("What are the differences between logs, metrics, and traces in monitoring systems?"),
                new Document("What are common strategies for observability in distributed systems?"),

                new Document("What are API gateways used for?"),
                new Document("What are features like rate limiting, caching, and routing in API gateways?"),
                new Document("What are the differences between Kong, Apigee, and AWS API Gateway?"),

                new Document("What are relational databases?"),
                new Document("What are the pros and cons of SQL vs NoSQL?"),
                new Document("What are normalization forms and why do they matter?"),

                new Document("What are the stages of software testing?"),
                new Document("What are the differences between unit, integration, and E2E testing?"),
                new Document("What are test doubles and how are they used in test-driven development?"),

                new Document("What are Kubernetes pods?"),
                new Document("What are the lifecycle stages of a pod?"),
                new Document("What are the differences between ReplicaSets, Deployments, and StatefulSets?"),

                new Document("What are queues in computer science?"),
                new Document("What are use cases for queues in distributed architecture?"),
                new Document("What are the trade-offs between RabbitMQ, Kafka, and SQS?"),

                new Document("What are content delivery networks (CDNs)?"),
                new Document("What are the benefits of using a CDN in global applications?"),
                new Document("What are edge caching strategies and how do they impact performance?"),

                new Document("What are embeddings in NLP?"),
                new Document("What are the differences between word2vec, GloVe, and BERT embeddings?"),
                new Document("What are vector similarity metrics like cosine and dot product used for?"),

                new Document("What are CI/CD tools available today?"),
                new Document("What are differences between Jenkins, GitHub Actions, and GitLab CI?"),
                new Document("What are self-hosted CI/CD pipelines and how do they compare to cloud-hosted ones?"),

                new Document("What are classes and objects in Java?"),
                new Document("What are the pillars of OOP: inheritance, polymorphism, encapsulation, abstraction?"),
                new Document("What are real-world modeling examples using classes and interfaces?"),

                new Document("What are JWTs (JSON Web Tokens)?"),
                new Document("What are the common vulnerabilities with JWTs and how are they mitigated?"),
                new Document("What are JWT claims and how are they verified securely in APIs?"),

                new Document("What are the principles of clean code?"),
                new Document("What are the key metrics for code quality and readability?"),
                new Document("What are refactoring strategies for legacy and tightly-coupled code?"),

                new Document("How does Spring Boot work?"),
                new Document("How does Spring Boot auto-configure beans during startup?"),
                new Document("How does Spring Boot use `@Conditional` annotations to dynamically load configurations?"),

                new Document("How do REST APIs handle client-server communication?"),
                new Document("How do REST APIs use HTTP methods like GET, POST, PUT, and DELETE?"),
                new Document("How do REST APIs implement pagination, filtering, and error handling?"),

                new Document("How is data stored in a relational database?"),
                new Document("How is data normalized to reduce redundancy and improve integrity?"),
                new Document("How is ACID compliance enforced during transactions in SQL databases?"),

                new Document("How does a neural network learn from data?"),
                new Document("How does backpropagation update weights in multilayer networks?"),
                new Document("How does gradient descent optimize loss functions in deep learning models?"),

                new Document("How do containers work?"),
                new Document("How do containers isolate processes and manage dependencies?"),
                new Document("How do layered file systems and union mounts operate under Docker?"),

                new Document("How is security handled in web applications?"),
                new Document("How is OAuth 2.0 used to authorize API access securely?"),
                new Document("How is CSRF protection implemented in Spring Security?"),

                new Document("How does garbage collection work in Java?"),
                new Document("How does the JVM manage heap, stack, and metaspace memory regions?"),
                new Document("How does the G1 garbage collector balance throughput and pause times?"),

                new Document("How are microservices deployed?"),
                new Document("How are microservices containerized and orchestrated using Kubernetes?"),
                new Document("How are rolling updates and blue-green deployments performed in CI/CD?"),

                new Document("How does Kubernetes manage workloads?"),
                new Document("How does the scheduler place pods onto nodes based on resource requests?"),
                new Document("How does Kubernetes handle autoscaling, liveness probes, and stateful apps?"),

                new Document("How are embeddings used in NLP?"),
                new Document("How are sentence embeddings compared using cosine similarity?"),
                new Document("How are vector indexes built using FAISS or PGVector for similarity search?"),

                new Document("How do load balancers distribute traffic?"),
                new Document("How do Layer 4 vs Layer 7 load balancers route requests?"),
                new Document("How do health checks and sticky sessions improve load balancing behavior?"),

                new Document("How does token-based authentication work?"),
                new Document("How does a JWT token encode identity and claims?"),
                new Document("How does a system verify and refresh tokens securely in distributed APIs?"),

                new Document("How is code tested in software development?"),
                new Document("How is unit testing used to validate function-level behavior?"),
                new Document("How is end-to-end testing automated using tools like Selenium or Cypress?"),

                new Document("How does CI/CD work?"),
                new Document("How does a CI/CD pipeline execute stages from build to deployment?"),
                new Document("How does GitHub Actions define workflows using YAML syntax?"),

                new Document("How do APIs handle rate limiting?"),
                new Document("How do token buckets and leaky bucket algorithms work for throttling?"),
                new Document("How do API gateways implement per-client quotas and retries?"),

                new Document("How are documents embedded using language models?"),
                new Document("How are transformer-based models like MiniLM used for embeddings?"),
                new Document("How are long documents chunked and aggregated using vector stores?"),

                new Document("How is logging implemented in microservices?"),
                new Document("How is structured logging configured using tools like Logback or Fluentd?"),
                new Document("How is distributed tracing enabled via OpenTelemetry and Jaeger?"),

                new Document("How does a JVM execute Java code?"),
                new Document("How does bytecode interpretation and JIT compilation improve performance?"),
                new Document("How does the JVM optimize hot methods with speculative inlining?"),

                new Document("How do LLMs process natural language?"),
                new Document("How do attention mechanisms model word importance in transformers?"),
                new Document("How do models like GPT use autoregression and causal masks for prediction?"),

                new Document("How does a CDN improve content delivery?"),
                new Document("How does edge caching reduce latency for end users?"),
                new Document("How does a CDN handle cache invalidation and regional load balancing?"),
                new Document("Explain cloud computing."),
                new Document("Explain how cloud computing differs from traditional on-premise infrastructure."),
                new Document("Explain cloud deployment models (IaaS, PaaS, SaaS) with real-world examples."),

                new Document("Tell me about Python programming."),
                new Document("Tell me about Python’s use in data science and automation."),
                new Document("Tell me about Python’s async programming with asyncio and its event loop."),

                new Document("What is artificial intelligence?"),
                new Document("What is the difference between artificial intelligence, machine learning, and deep learning?"),
                new Document("What is reinforcement learning and how is it used in autonomous systems?"),

                new Document("Help me understand NoSQL databases."),
                new Document("Help me understand key differences between NoSQL and relational databases."),
                new Document("Help me understand document stores like MongoDB and how they handle scalability."),

                new Document("Describe frontend vs backend development."),
                new Document("Describe the responsibilities of frontend and backend in full-stack web apps."),
                new Document("Describe how React (frontend) and Node.js (backend) communicate in a MERN stack."),

                new Document("Give an overview of DevOps."),
                new Document("Give an overview of the tools used in a DevOps pipeline."),
                new Document("Give an overview of GitOps and how it works with Kubernetes deployments."),

                new Document("What is cloud-native development?"),
                new Document("What is the role of containers and orchestration in cloud-native design?"),
                new Document("What is the 12-factor app methodology in cloud-native architecture?"),

                new Document("Explain how ChatGPT works."),
                new Document("Explain how ChatGPT uses transformer models and attention mechanisms."),
                new Document("Explain how ChatGPT handles conversational memory and context windows."),

                new Document("Tell me how to build a REST API."),
                new Document("Tell me how to build a secure REST API using Spring Boot."),
                new Document("Tell me how to implement token-based authentication in a Spring REST API."),

                new Document("What are coding best practices?"),
                new Document("What are clean code principles every developer should follow?"),
                new Document("What are SOLID principles and how are they applied in object-oriented design?"),

                new Document("Write something about neural networks."),
                new Document("Write something about how multilayer perceptrons work."),
                new Document("Write something about how convolutional layers process image data in CNNs."),

                new Document("Summarize the benefits of Docker."),
                new Document("Summarize how Docker supports isolated environments for testing."),
                new Document("Summarize how Docker Compose helps orchestrate multi-container setups."),

                new Document("What do you know about OpenAI?"),
                new Document("What do you know about OpenAI's models like GPT and Codex?"),
                new Document("What do you know about the architecture and safety layers used in GPT-4?"),

                new Document("Can you explain semantic search?"),
                new Document("Can you explain how embeddings are used in semantic search?"),
                new Document("Can you explain the difference between lexical search and vector search?"),

                new Document("How do I prepare for coding interviews?"),
                new Document("How do I prepare for system design rounds in coding interviews?"),
                new Document("How do I prepare with LeetCode and design scalable systems for interviews?"),

                new Document("What is Kubernetes used for?"),
                new Document("What is the role of kubelet, scheduler, and controller manager in Kubernetes?"),
                new Document("What is a Helm chart and how does it simplify app deployment in Kubernetes?"),

                new Document("Show me examples of Java streams."),
                new Document("Show me how Java streams are used for filtering and mapping collections."),
                new Document("Show me how to write custom collectors and parallel streams in Java 17."),

                new Document("What is the difference between RAM and ROM?"),
                new Document("What is the practical impact of RAM vs ROM in everyday computing?"),
                new Document("What is the role of volatile vs non-volatile memory in hardware systems?"),

                new Document("Explain version control."),
                new Document("Explain how Git handles branches and merges."),
                new Document("Explain how rebasing differs from merging in Git workflows."),

                new Document("What are good use cases for AI?"),
                new Document("What are good use cases for AI in enterprise software development?"),
                new Document("What are good use cases for AI-driven personalization in e-commerce systems?")


        );

        vectorStore.add(documents);
    }
}
