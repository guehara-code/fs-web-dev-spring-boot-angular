package com.example.springboothelloworldexample;

import com.example.springboothelloworldexample.config.ConfigProperties;
import com.example.springboothelloworldexample.exception.UrlAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

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

	@EventListener(classes = ContextRefreshedEvent.class)
	public void listen() {
		// check if the url is accessible
		// if ok
		throw new UrlAccessException("http://www.example.com");
	}
}
