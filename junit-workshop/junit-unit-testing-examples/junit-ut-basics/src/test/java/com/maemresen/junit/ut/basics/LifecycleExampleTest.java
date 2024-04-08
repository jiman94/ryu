package com.maemresen.junit.ut.basics;

// Import necessary JUnit 5 annotations and methods.
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LifecycleExampleTest {

    // @BeforeAll annotated method will be run once before all test methods in this class.
    // It needs to be static because it is run when the test class is initialized, before any objects are created.
    @BeforeAll
    static void beforeAll() {
        System.out.println("This will be executed once before all tests executed");
    }

    // @BeforeEach annotated method will be run before each test method.
    // It's used to set up the test environment for each test (e.g., initialize instance variables).
    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        System.out.println("Executed before " + testInfo.getDisplayName());
    }

    // @AfterAll annotated method will be run once after all test methods in this class have run.
    // It needs to be static because it's run after all the tests have been completed and all objects have been cleaned up.
    @AfterAll
    static void afterAll() {
        System.out.println("This will be executed once after all tests executed");
    }

    // @AfterEach annotated method will be run after each test method.
    // It's used to clean up the test environment after each test (e.g., reset instance variables or release resources).
    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("Executed after " + testInfo.getDisplayName());
    }

    // A test method with a custom name "Test1" that demonstrates a basic equality assertion.
    @Test
    @DisplayName("Test1 - Basic Equality Assertion")
    void test1() {
        String str = "Junit is working fine";
        // This assertion checks that the string "str" is equal to "Junit is working fine".
        assertEquals("Junit is working fine", str);
    }

    // A test method with a custom name "Test2" that demonstrates a basic boolean assertion.
    @Test
    @DisplayName("Test2 - Basic Boolean Assertion")
    void test2() {
        boolean condition = true;
        // This assertion checks that the condition is true.
        assertTrue(condition, "Expected condition to be true");
    }

    // A test method with a custom name "Test3" that demonstrates a basic non-equality assertion.
    @Test
    @DisplayName("Test3 - Basic Non-Equality Assertion")
    void test3() {
        int firstNumber = 5;
        int secondNumber = 7;
        // This assertion checks that the two numbers are not equal.
        assertNotEquals(firstNumber, secondNumber, "Expected the two numbers to be different");
    }
}
