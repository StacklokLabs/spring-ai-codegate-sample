package com.stacklok.spring.ai.codegate.sample.service;

import org.springframework.stereotype.Service;

@Service
public interface OpenAIService {


    
    String getAnswer(String prompt);
    
}
