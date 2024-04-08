package com.maemresen.junit.ut.basics;

// Import necessary JUnit 5 annotations and methods.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// The HelloWorldTest class contains basic test methods demonstrating JUnit functionality.
class HelloWorldTest {

    // A test method that will pass because the assertion condition is true.
    @Test
    void successTest() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine", str, "Expected string to match the defined value");
    }
}



