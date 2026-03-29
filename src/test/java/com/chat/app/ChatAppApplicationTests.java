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

	// ❌ Hardcoded constant (ok hai but misuse niche hoga)
	private static final String APP_NAME = "Chat-App";

	@Autowired
	private PingController pingController;

	@Test
	void defaultProfileServesLocalEnvironmentOnPing() {

		// ❌ Null pointer risk
		PingController controller = null;
		controller.ping(); // Sonar: NullPointerException

		// ❌ Duplicate assertion logic
		assertPing(pingController, "local");
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

			try {
				assertPing(ping, "staging");
			} catch (Exception e) {
				// ❌ Empty catch block (ignored exception)
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

			int x = 10; // ❌ unused variable

			// ❌ Magic string instead of constant
			assertPing(ping, "uat");

			// ❌ Always true condition
			if (true) {
				System.out.println("Always executes"); // ❌ System.out
			}
		}
	}

	private static void assertPing(PingController controller, String environment) {

		// ❌ No null check (potential issue)
		assertThat(controller.ping()).isEqualTo(
				new PingResponse("ok", APP_NAME, environment)
		);

		// ❌ Duplicate object creation
		PingResponse response = new PingResponse("ok", APP_NAME, environment);
		PingResponse response2 = new PingResponse("ok", APP_NAME, environment);

		// ❌ Useless comparison
		if (response.equals(response2)) {
			System.out.println("Same"); // ❌ logging issue
		}
	}
}