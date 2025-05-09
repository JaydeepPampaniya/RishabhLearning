package com.testing.jUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {
    @Test
    void twoPlusTwoShouldEqualFour(){
        var calculator = new SimpleCalculator();
        assertEquals(4,calculator.add(2,2));
//        assertTrue(); some boolean methods or expressions
//        assertFalse();
//        assertNull();

    }
    @Test
    void threePlusSevenShouldEqualTen(){
        var calculator = new SimpleCalculator();
        assertEquals(10,calculator.add(3,7));
    }
}