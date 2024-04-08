package com.maemresen.junit.ut.basics.ordered;

import org.junit.jupiter.api.Test;

// This class doesn't specify a custom method order.
// Therefore, JUnit may use the default orderer or any orderer specified in a configuration file.
// Without a specific orderer, there's no guaranteed execution order.
class WithoutOrderTest {

    @Test
    void test3() {
        System.out.println("test3");
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }
}
