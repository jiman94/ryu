package com.maemresen.junit.ut.pt;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvSourceTest {
    static class LocalDateConverter implements ArgumentConverter {
        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            if(source instanceof String dateString) {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            }
            throw new ArgumentConversionException(source + " is not a string");
        }
    }

    @ParameterizedTest(name = "getYear({0}) should return {1}")
    @CsvSource(value = {"2012/12/25:2012", "2013/12/25:2013", "2014/12/25:2014"}, delimiter = ':')
    void csvSourceTest(@JavaTimeConversionPattern("yyyy/MM/dd") LocalDate date, int expected) {
        assertEquals(expected, date.getYear());
    }

    @ParameterizedTest(name = "getYear({0}) should return {1}")
    @CsvSource(value = {"2012/12/25:2012", "2013/12/25:2013", "2014/12/25:2014"}, delimiter = ':')
    void csvSourceTestWithCustomConverter(@ConvertWith(LocalDateConverter.class) LocalDate date, int expected) {
        assertEquals(expected, date.getYear());
    }
}
