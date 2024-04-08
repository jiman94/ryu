package com.maemresen.it.sb.basic.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CalculatorService {

    private final MathService mathService;
    private final TrigonometryService trigonometryService;

    public int add(int a, int b) {
        return mathService.add(a, b);
    }

    public int subtract(int a, int b) {
        return mathService.subtract(a, b);
    }

    public int multiply(int a, int b) {
        return mathService.multiply(a, b);
    }

    public double sin(double a) {
        return trigonometryService.sin(a);
    }

    public double cos(double a) {
        return trigonometryService.cos(a);
    }
}
