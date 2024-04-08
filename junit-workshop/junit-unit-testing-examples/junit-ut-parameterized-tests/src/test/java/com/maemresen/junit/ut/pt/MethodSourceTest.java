package com.maemresen.junit.ut.pt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MethodSourceTest {

    private static Stream<Arguments> nonBlankStringsProvider() {
        return Stream.of(
                Arguments.of("method-a"),
                Arguments.of("method-b"),
                Arguments.of("method-c")
        );
    }

    @ParameterizedTest
    @MethodSource("nonBlankStringsProvider")
    void methodSourceTest(String str) {
        assertFalse(str == null || str.trim().isEmpty());
    }
}
