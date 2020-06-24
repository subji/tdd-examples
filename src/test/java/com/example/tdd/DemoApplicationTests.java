package com.example.tdd;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	private static final Logger log = Logger.getLogger(DemoApplicationTests.class.getName());

	@BeforeAll
	static void setup() {
		log.info("@BeforeAll - executes once before all test methods in this class");
	}

	@BeforeEach
	void init() {
		log.info("@BeforeEach - executes before each test method in this class");
	}

	@Test
	@DisplayName("1 + 1 = 2")
	void addTwoNumbers() {
		Calculator calculator = new Calculator();
		assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
	}

	@DisplayName("Single test successful")
	@Test
	void testSingleSuccessTest() {
		log.info("Success");
	}

	@Test
	@Disabled("Not implemented yet")
	void testShowSomething() {
	}

	@Test
	void lambdaExpressions() {
		List<Integer> numbers = Arrays.asList(1, 2, 3);

		assertTrue(numbers.stream().mapToInt(i -> i).sum() > 5, () -> "Sum should be greater than 5");
	}

	@Test
	void groupAssertions() {
		int[] numbers = { 0, 1, 2, 3, 4 };

		assertAll("numbers", () -> assertEquals(numbers[0], 1), () -> assertEquals(numbers[3], 3),
				() -> assertEquals(numbers[4], 1));
	}

	@Test
	void trueAssumption() {
		assumeTrue(5 > 1);
		assertEquals(5 + 2, 7);
	}

	@Test
	void falseAssumption() {
		assumeFalse(5 < 1);
		assertEquals(5 + 2, 7);
	}

	@Test
	void assumptionThat() {
		String someString = "Just a string";
		assumingThat(someString.equals("Just a string"), () -> assertEquals(2 + 1, 4));
	}

	@Test
	void shouldThrowException() {
		Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
			throw new UnsupportedOperationException("Not supported");
		});
		assertEquals(exception.getMessage(), "Not supported");
	}

	@Test
	void assertThrowsException() {
		String str = null;
		assertThrows(IllegalArgumentException.class, () -> {
			Integer.valueOf(str);
		});
	}

	private List<String> in = new ArrayList<>(Arrays.asList("A", "B", "C"));
	private List<String> out = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));

	@TestFactory
	public Stream<DynamicTest> translateDynamicTestsFromStream() {
		return in.stream().map(word -> DynamicTest.dynamicTest("Test translate " + word, () -> {
			int id = in.indexOf(word);
			assertEquals(out.get(id), translate(word));
		}));
	}

	private String translate(String word) {
		if ("A".equalsIgnoreCase(word)) {
			return "aaa";
		} else if ("B".equalsIgnoreCase(word)) {
			return "bbb";
		} else if ("C".equalsIgnoreCase(word)) {
			return "ccc";
		}
		return "Error";
	}

	@AfterEach
	void tearDown() {
		log.info("@AfterEach - executed after each test method.");
	}

	@AfterAll
	static void done() {
		log.info("@AfterAll - executed after all test methods.");
	}

}
