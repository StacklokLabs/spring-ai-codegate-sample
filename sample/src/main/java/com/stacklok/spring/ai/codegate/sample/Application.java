package com.stacklok.spring.ai.codegate.sample;

import java.util.Scanner;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean	
	public CommandLineRunner cli(@Value("classpath:senstive-contact-test-data.pdf") Resource senstiveTestData,
			ChatClient.Builder chatClientBuilder) {

		return args -> {

			// 1. Load the hurricane documents in vector store
			//vectorStore.add(new TokenTextSplitter().split(new PagePdfDocumentReader(hurricaneDocs).read()));

			// 2. Create the ChatClient with chat memory and RAG support
			var chatClient = chatClientBuilder
				.defaultSystem("I'm your privacy specialist.  Let's go! ") // Set the system prompt
				.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())) // Enable chat memory
				//.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore)) // Enable RAG
				.build();

			// 3. Start the chat loop
			System.out.println("\nI am your privacy assitant.\n");
			try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
					System.out.print("\nUSER: ");
					System.out.println("\nASSISTANT: " + 
						chatClient.prompt(scanner.nextLine()) // Get the user input
							.call()
							.content());
				}
			}
		};
	}
}

