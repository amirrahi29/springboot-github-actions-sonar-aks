package com.chat.app.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {

	private final String applicationName;
	private final String environment;

	public PingController(
			@Value("${spring.application.name}") String applicationName,
			@Value("${app.environment:local}") String environment) {
		this.applicationName = applicationName;
		this.environment = environment;
	}

	@GetMapping("/ping")
	public PingResponse ping() {
		return new PingResponse("ok", applicationName, environment);
	}
}
