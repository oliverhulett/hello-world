package com.cap.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		Assertions.fail("Test does not fail, even if DB doesn't exist or has wrong schema");
	}
}
