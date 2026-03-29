package com.chat.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.chat.app.api.PingController;
import com.chat.app.api.PingResponse;

@SpringBootTest
class ChatAppApplicationTests {

	@Autowired
	private PingController pingController;

	@Test
	void contextLoads() {
		assertThat(pingController).isNotNull();
	}

	@Test
	void defaultProfileUsesLocalEnvironment() {
		assertThat(pingController.ping())
				.isEqualTo(new PingResponse("ok", "Chat-App", "local"));
	}

	@Nested
	@SpringBootTest
	@ActiveProfiles("staging")
	class StagingProfile {

		@Autowired
		private PingController ping;

		@Test
		void loadsStagingEnvironmentFromProfile() {
			assertThat(ping.ping())
					.isEqualTo(new PingResponse("ok", "Chat-App", "staging"));
		}
	}

	@Nested
	@SpringBootTest
	@ActiveProfiles("uat")
	class UatProfile {

		@Autowired
		private PingController ping;

		@Test
		void loadsUatEnvironmentFromProfile() {
			assertThat(ping.ping())
					.isEqualTo(new PingResponse("ok", "Chat-App", "uat"));
		}
	}
}
