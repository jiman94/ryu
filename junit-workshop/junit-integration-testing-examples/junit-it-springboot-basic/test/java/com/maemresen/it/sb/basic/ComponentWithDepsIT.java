package com.maemresen.it.sb.basic;

import com.maemresen.it.sb.basic.calculator.CalculatorService;
import com.maemresen.it.sb.basic.calculator.MathService;
import com.maemresen.it.sb.basic.calculator.TrigonometryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {CalculatorService.class, MathService.class, TrigonometryService.class})
class ComponentWithDepsIT {

    @Autowired
    private CalculatorService calculatorService;

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

    @Test
    void testSin() {
        double result = calculatorService.sin(30);
        assertEquals(Math.sin(Math.toRadians(30)), result);
    }

    @Test
    void testCos() {
        double result = calculatorService.cos(90);
        assertEquals(Math.cos(Math.toRadians(90)), result);
    }
}
