package com.example.springboothelloworldexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootHelloWorldExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" *** Show arguments *** ");
		Arrays.stream(args).forEach(System.out::println);
	}
}
