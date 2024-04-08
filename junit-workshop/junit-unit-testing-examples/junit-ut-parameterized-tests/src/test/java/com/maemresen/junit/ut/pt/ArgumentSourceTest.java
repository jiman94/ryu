package com.maemresen.junit.ut.pt;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ArgumentSourceTest {

    static class NonBlankRandomStringArgumentProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("argumentProvider-a"),
                    Arguments.of("argumentProvider-b"),
                    Arguments.of("argumentProvider-c")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(NonBlankRandomStringArgumentProvider.class)
    void argumentSourceTest(String str) {
        assertFalse(str == null || str.trim().isEmpty());
    }

    @Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @ArgumentsSource(NonBlankRandomStringArgumentProvider.class)
    @interface NonBlankRandomStringSource {
    }

    @ParameterizedTest
    @NonBlankRandomStringSource
    void customAnnotationSourceTest(String str) {
        assertFalse(str == null || str.trim().isEmpty());
    }
}
