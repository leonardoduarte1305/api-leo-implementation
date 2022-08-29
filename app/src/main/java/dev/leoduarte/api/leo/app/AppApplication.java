package dev.leoduarte.api.leo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}

//TODO Write the readme.md file.
//TODO Update the Jenkinsfile.
//TODO Update the Dockerfile.
//TODO Update Grafana Dashboard
//TODO Implement Flyway
//TODO Unit tests
//TODO Implement TestContainers
//TODO Implement integration tests
//TODO Implement a Consumer and the producer for the email
//TODO Implement Send email after create an employee using RabbitMQ
//TODO Handle the events
//TODO Implement Spring Security
