package com.maemresen.it.sb.basic;

import com.maemresen.it.sb.basic.calculator.MathService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MathService.class)
class BasicComponentIT {

    @Autowired
    private MathService calculatorService;

    @Test
    void testAdd() {
        int result = calculatorService.add(1, 2);
        assertEquals(3, result);
    }

    @Test
    void testSubtract() {
        int result = calculatorService.subtract(1, 2);
        assertEquals(-1, result);
    }

    @Test
    void testMultiply() {
        int result = calculatorService.multiply(1, 2);
        assertEquals(2, result);
    }

}
