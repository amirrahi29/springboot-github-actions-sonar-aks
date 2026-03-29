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

	private static final String APP_NAME = "Chat-App";

	@Autowired
	private PingController pingController;

	@Test
	void defaultProfileServesLocalEnvironmentOnPing() {
		assertPing(pingController, "local");
	}

	@Nested
	@SpringBootTest
	@ActiveProfiles("staging")
	class StagingProfile {

		@Autowired
		private PingController ping;

		@Test
		void activeProfileReflectsInPing() {
			assertPing(ping, "staging");
		}
	}

	@Nested
	@SpringBootTest
	@ActiveProfiles("uat")
	class UatProfile {

		@Autowired
		private PingController ping;

		@Test
		void activeProfileReflectsInPing() {
			assertPing(ping, "uat");
		}
	}

	private static void assertPing(PingController controller, String environment) {
		assertThat(controller.ping()).isEqualTo(new PingResponse("ok", APP_NAME, environment));
	}
}
