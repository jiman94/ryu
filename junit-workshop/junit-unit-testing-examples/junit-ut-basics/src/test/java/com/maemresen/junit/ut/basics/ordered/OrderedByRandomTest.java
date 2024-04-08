package com.maemresen.junit.ut.basics.ordered;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// This class specifies a custom method order using the Random orderer.
// The tests in this class will be executed in a random order every time they are run.
@TestMethodOrder(MethodOrderer.Random.class)
class OrderedByRandomTest {

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
