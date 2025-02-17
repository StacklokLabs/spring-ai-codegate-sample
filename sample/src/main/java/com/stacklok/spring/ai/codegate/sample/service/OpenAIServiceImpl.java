package com.stacklok.spring.ai.codegate.sample.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;
@Service
public class OpenAIServiceImpl implements OpenAIService {
    private final ChatClient chatClient;
   
    public OpenAIServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
        .defaultSystem("As an Engineer, design an AI system that prioritizes user privacy" + 
        "by minimizing data collection, processing data locally where possible, and " + 
        "implementing strong security measures. Ensure transparency, compliance with privacy " +  
        "regulations, and give users control over their data.") // Set the system prompt
        .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())) // Enable chat memory
        .build();
    }

    @Override
    public String getAnswer(String question) {
        String response = this.chatClient.prompt()
                .user(question)
                .call()
                .content();
        return response;
    }

}
