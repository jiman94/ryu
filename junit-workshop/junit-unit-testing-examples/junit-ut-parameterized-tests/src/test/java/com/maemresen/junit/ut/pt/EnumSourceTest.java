package com.maemresen.junit.ut.pt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnumSourceTest {
    @ParameterizedTest(name = "{0}.getValue() must be between 1 and 12")
    @EnumSource(Month.class)
    void enumSourceTest(Month month) {
        final var monthNumber = month.getValue();
        assertAll(
                () -> assertTrue(monthNumber >= 1),
                () -> assertTrue(monthNumber <= 12));
    }

    @ParameterizedTest(name = "{0} must have 30 days")
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void enumSourceOnlyIncludedTest(Month month) {
        assertEquals(30, month.length(false));
    }

    @ParameterizedTest(name = "{0} must have 31 days")
    @EnumSource(
            value = Month.class,
            names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"},
            mode = EnumSource.Mode.EXCLUDE
    )
    void enumSourceWithoutExcludedTest(Month month) {
        assertEquals(31, month.length(false));
    }

    @ParameterizedTest(name = "{0} must ends with BER")
    @EnumSource(
            value = Month.class,
            names = ".+BER",
            mode = EnumSource.Mode.MATCH_ANY
    )
    void enumSourceOnlyMatchingNameTest(Month month) {
        assertTrue(month.name().endsWith("BER"));
    }
}
