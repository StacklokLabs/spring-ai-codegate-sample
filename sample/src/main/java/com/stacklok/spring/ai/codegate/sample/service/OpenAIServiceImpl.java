package com.stacklok.spring.ai.codegate.sample.service;

import org.springframework.ai.chat.model.ChatModel;

public class OpenAIServiceImpl implements OpenAIService {
    private final ChatModel model;
   
    public OpenAIServiceImpl(ChatModel model) {
        this.model = model;
    }

    @Override
    public String getModelResponse(String prompt) {
        return model.call(prompt);
    }

}
