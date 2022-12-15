package dev.leoduarte.api.leo.app.controller;

import java.time.Instant;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/")
@RestController
public class Health {

	@GetMapping
	public String hello() {
		return "I'm online and the server time is: " + Instant.now().toString();
	}
}
