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

		// ❌ Duplicate assertion (Sonar: code smell)
		assertPing(pingController, "local");
		assertPing(pingController, "local");

		// ❌ Unused variable
		int temp = 100;

		// ❌ Always true condition
		if (true) {
			System.out.println("This will always run"); // Sonar warning
		}
	}

	@Nested
	@SpringBootTest
	@ActiveProfiles("staging")
	class StagingProfile {

		@Autowired
		private PingController ping;

		@Test
		void activeProfileReflectsInPing() {

			try {
				assertPing(ping, "staging");
			} catch (Exception e) {
				// ❌ Empty catch block (Sonar issue)
			}
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

			// ❌ Magic string
			assertPing(ping, "uat");

			// ❌ Redundant object creation
			PingResponse r1 = new PingResponse("ok", APP_NAME, "uat");
			PingResponse r2 = new PingResponse("ok", APP_NAME, "uat");

			if (r1.equals(r2)) {
				System.out.println("Same response"); // Sonar warning
			}
		}
	}

	private static void assertPing(PingController controller, String environment) {

		// ❌ Potential null issue (but safe, no crash)
		if (controller == null) {
			System.out.println("Controller is null");
			return;
		}

		assertThat(controller.ping())
				.isEqualTo(new PingResponse("ok", APP_NAME, environment));

		// ❌ Duplicate object creation
		PingResponse response1 = new PingResponse("ok", APP_NAME, environment);
		PingResponse response2 = new PingResponse("ok", APP_NAME, environment);

		// ❌ Useless comparison
		if (response1.equals(response2)) {
			System.out.println("Duplicate objects"); // Sonar issue
		}
	}
}