package com.stacklok.spring.ai.codegate.sample;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.stacklok.spring.ai.codegate.sample.service.OpenAIService;
/*
 * This CLI application serves as a privacy assistant leveraging AI technology to handle sensitive data. 
 * This application combines the capabilties of two open source projects: Spring AI and Codegate.
 * Spring AI is a framework that simplifies the integration of AI capabilities into Spring applications
 * and Codegate is a transparent AI gateway that helps prevent the leakage ofpotentially sensitive data.
 * spring-ai: https://github.com/spring-projects/spring-ai
 * codegate: https://github.com/stacklok/codegate
 */

@SpringBootApplication
public class Application {

	private final OpenAIService openAIService;

	public Application(OpenAIService openAIService) {
		this.openAIService = openAIService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	/*
	* This method is used to create a simple command line interface (CLI) that will be used to 
	* interact with the gateway and the AI model.
	*/
	@Bean
	public CommandLineRunner cli() {
		return args -> {
			System.out.println("========================================================================");
			System.out.println("=============== SPRING AI with Codegate privacy assistant ==============");
			System.out.println("========================================================================");

			// Start the chat loop
			System.out.println("\nHow can I assist you today?\n");
			try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
					System.out.print("\nUser: ");
					System.out.println("\nAssistant: "
							+ openAIService.getAnswer(scanner.nextLine()));

				}
			}
		};
	}
}
