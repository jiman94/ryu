package com.maemresen.junit.ut.basics.ordered;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// This class specifies a custom method order using the OrderAnnotation orderer.
// The tests in this class will be executed based on the @Order annotation.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedByOrderAnnotationTest {

    @Test
    @Order(2)
    void test1() {
        System.out.println("test1");
    }

    @Test
    @Order(0)
    void test2() {
        System.out.println("test2");
    }

    @Test
    @Order(1)
    void test3() {
        System.out.println("test3");
    }
}
