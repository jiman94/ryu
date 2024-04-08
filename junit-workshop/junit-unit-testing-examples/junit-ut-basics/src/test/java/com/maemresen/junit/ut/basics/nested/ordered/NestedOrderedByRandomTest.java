package com.maemresen.junit.ut.basics.nested.ordered;

// Import necessary JUnit 5 annotations and class orderer.
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

// The @TestClassOrder annotation specifies the order in which nested test classes are executed.
// In this case, the ClassOrderer.Random.class is used, meaning the nested test classes will be executed
// in a random order every time the tests are run.
@TestClassOrder(ClassOrderer.Random.class)
class NestedOrderedByRandomTest {

    // NestedTest1 is a nested test class without a specified order.
    // Because of the ClassOrderer.Random.class setting, its execution order relative to other nested classes
    // will be random.
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

    // NestedTest2, like NestedTest1, is a nested test class without a specified order.
    // Its relative execution order will also be determined randomly.
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
