package com.lcwd.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringHelperParameterizedTest {

    StringHelper helper = new StringHelper();

    static Stream<Arguments> testConditions() {
        return Stream.of(
                Arguments.of("AACD", "CD"),
                Arguments.of("ACD", "CD")
        );
    }

    @ParameterizedTest
    @MethodSource("testConditions")
    void testTruncateAInFirst2Positions(String input, String expected) {
        Assertions.assertEquals(expected, helper.truncateAInFirst2Positions(input));
    }


}
