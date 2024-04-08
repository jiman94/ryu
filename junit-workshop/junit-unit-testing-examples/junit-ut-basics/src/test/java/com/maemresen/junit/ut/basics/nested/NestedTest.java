package com.maemresen.junit.ut.basics.nested;

// Import necessary JUnit 5 annotations.
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

// The NestedTest class demonstrates the use of JUnit's @Nested annotation
// to create nested test classes for better organization and readability.
class NestedTest {

    // @Nested annotation indicates that this class is a nested test class.
    // Nested test classes are non-static inner classes of a top-level test class.
    // Each nested class can contain multiple test methods.
    @Nested
    class NestedTest1 {

        // A simple test method within the NestedTest1 class.
        @Test
        void nestedTest1_test1() {
            System.out.println("nestedTest1_test1");
        }

        // Another simple test method within the NestedTest1 class.
        @Test
        void nestedTest1_test2() {
            System.out.println("nestedTest1_test2");
        }
    }

    // Another nested test class, separate from NestedTest1.
    // This allows for a second group of related tests within the same top-level test class.
    @Nested
    class NestedTest2 {

        // A simple test method within the NestedTest2 class.
        @Test
        void nestedTest2_test1() {
            System.out.println("nestedTest2_test1");
        }

        // Another simple test method within the NestedTest2 class.
        @Test
        void nestedTest2_test2() {
            System.out.println("nestedTest2_test2");
        }
    }
}
