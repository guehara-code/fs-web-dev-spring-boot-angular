package com.example.springboothelloworldexample;

import com.example.springboothelloworldexample.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootHelloWorldExampleApplication implements ApplicationRunner {

	@Autowired
	private ConfigProperties configProperties;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldExampleApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(" *** mail properties *** ");
		System.out.println("From: " + configProperties.getFrom());
		System.out.println("To: " + configProperties.getTo());
		System.out.println("Default object: " + configProperties.getDefaultObject());
	}
}
