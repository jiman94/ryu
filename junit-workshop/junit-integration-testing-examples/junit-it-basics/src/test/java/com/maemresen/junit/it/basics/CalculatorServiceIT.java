package com.maemresen.junit.it.basics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServiceIT {

    @Test
    void testAdd() {
        CalculatorService calculator = new CalculatorService();
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }
}
