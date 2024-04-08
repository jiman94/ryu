package com.maemresen.junit.ut.pt;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

class SimpleParameterizedTest {
    @ParameterizedTest
    @NullSource
    void nullSourceTest(String str) {
        assertNull(str);
    }

    @ParameterizedTest
    @EmptySource
    void emptyStringTest(String str) {
        assertTrue(str.trim().isEmpty());
    }

    @ParameterizedTest
    @EmptySource
    void emptyListTest(List<?> list) {
        assertTrue(list.isEmpty());
    }

    @ParameterizedTest
    @EmptySource
    void emptySetTest(Set<?> set) {
        assertTrue(set.isEmpty());
    }

    @ParameterizedTest
    @EmptySource
    void emptyMapTest(Map<?, ?> map) {
        assertTrue(map.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptySourceTest(String str) {
        assertTrue(str == null || str.trim().isEmpty());
    }
}