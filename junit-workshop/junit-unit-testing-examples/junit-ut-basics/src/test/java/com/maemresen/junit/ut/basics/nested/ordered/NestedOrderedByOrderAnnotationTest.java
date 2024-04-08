package com.maemresen.junit.ut.basics.nested.ordered;

// Import necessary JUnit 5 annotations and class orderer.
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

// The @TestClassOrder annotation specifies the order in which nested test classes are executed.
// In this case, the ClassOrderer.OrderAnnotation class is used to determine the order based on the @Order annotations
// on the nested test classes.
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class NestedOrderedByOrderAnnotationTest {

    // This nested test class is annotated with @Order(2),
    // meaning it will be executed after any lower-numbered (e.g., @Order(1)) nested test classes.
    @Nested
    @Order(2)
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

    // This nested test class is annotated with @Order(1),
    // meaning it will be executed before any higher-numbered (e.g., @Order(2)) nested test classes.
    @Nested
    @Order(1)
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
