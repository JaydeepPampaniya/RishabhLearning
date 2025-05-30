package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(1)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FizzBuzzTest {

    // If number is divisible by 3, print Fizz
    @DisplayName("Divisible by Three")
    @Test
    @Order(1)
    void testForDivisibleByThree() {

        String expected = "Fizz";

        assertEquals(expected, FizzBuzz.compute(3), "Should return Fizz");
    }

    // If number is divisible by 5, print Buzz
    @DisplayName("Divisible by Five")
    @Test
    @Order(2)
    void testForDivisibleByFive() {

        String expected = "Buzz";

        assertEquals(expected, FizzBuzz.compute(5), "Should return Buzz");
    }

    // If number is divisible by 3 and 5, print FizzBuzz
    @DisplayName("Divisible by Three and Five")
    @Test
    @Order(3)
    void testForDivisibleByThreeAndFive() {

        String expected = "FizzBuzz";

        assertEquals(expected, FizzBuzz.compute(15), "Should return FizzBuzz");
    }

    // If number is NOT divisible by 3 or 5, then print the number
    @DisplayName("Not Divisible by Three or Five")
    @Test
    @Order(4)
    void testForNotDivisibleByThreeOrFive() {

        String expected = "1";

        assertEquals(expected, FizzBuzz.compute(1), "Should return 1");
    }

    @DisplayName("Testing with Small data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources="/small-test-data.csv")
    @Order(5)
    void testSmallDataFile(int value, String expected) {

        assertEquals(expected, FizzBuzz.compute(value));

    }

    @DisplayName("Testing with Medium data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources="/medium-test-data.csv")
    @Order(6)
    void testMediumDataFile(int value, String expected) {

        assertEquals(expected, FizzBuzz.compute(value));

    }

    @DisplayName("Testing with Large data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources="/large-test-data.csv")
    @Order(7)
    void testLargeDataFile(int value, String expected) {

        assertEquals(expected, FizzBuzz.compute(value));

    }

    @DisplayName("Testing with Large data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @MethodSource("inputValuesForFizzBuzzTestings")
    @Order(7)
    void testInputData(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }

    private static Stream<Arguments> inputValuesForFizzBuzzTestings(){
        return Stream.of(Arguments.of(32,"32"),
                Arguments.of(23,"23"),
                Arguments.of(35,"Buzz"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Jaydeep","aashish"})
    void valueSourceDemonstration(String name){
        assertNotNull(name,()->"name should be not null");

    }

    @DisplayName("Testing with Repetition")
    @RepeatedTest(value=3,name="{DisplayName}, Repetition {currentRepetition} of " + "{totalRepetition}")
    void testing_WithRepetition(RepetitionInfo repetitionInfo,TestInfo testInfo) {
        System.out.println("test Info"+ testInfo.getTestMethod().get().getName());
        System.out.println("current Repetition : "+ repetitionInfo.getCurrentRepetition() + "total repetition:" + repetitionInfo.getTotalRepetitions());
        assertEquals("1", FizzBuzz.compute(1));
    }


}






