package net.jqwik2.core.api;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class JqwikVersionTests {

	@Test
	void currentJqwikVersionIs2() {
		assertThat(Jqwik.version()).isEqualTo("2");
	}
}
