package com.lcwd.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {
    StringHelper helper=new StringHelper();
    @Test // test annotation only support void return type in methods
    void testTruncateAInFirst2Positions_AinFirst2Positions(){
        assertEquals("CD",helper.truncateAInFirst2Positions("AACD"));
    }

    @Test
    void test(){
        StringHelper helper=new StringHelper();
        assertEquals("CD",helper.truncateAInFirst2Positions("AACD"));
    }

    @Test
    void testAreFirstAndLastTwoCharactersTheSame_BasicNegativeScenario(){
        boolean actualValue = helper.areFirstAndLastTwoCharactersTheSame("ABCA");
        assertFalse(actualValue);
    }

    @Test
    void testAreFirstAndLastTwoCharactersTheSame_BasicPositiveScenario(){
        boolean actualValue = helper.areFirstAndLastTwoCharactersTheSame("ABAB");
        assertTrue(actualValue);
    }

    @Test
    void testArraySort(){
        int[] numbers = {12,3,4,1};
        int[] expected = {1,3,4,12};
        Arrays.sort(numbers);
        assertArrayEquals(expected,numbers);
    }

    @Test
    @Timeout(2)
    void testArraySort_NullArray(){
        int[] numbers = null;
        try
        {
            Arrays.sort(numbers);
        } catch (Exception e) {

        }
        assertTimeout(Duration.ofSeconds(2), () -> {
            // Simulating a long-running task
            Thread.sleep(1000); // 1 second (less than 2s, so test passes)
        });
//        assertArrayEquals(expected,numbers);
    }

}