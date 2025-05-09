package com.testing.jUnit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class determineLetterGradeTest {
    @Test
    void fiftyNineShouldReturnF() throws IllegalAccessException {
        var grade = new DetermineLetterGrade();
        assertEquals('F',grade.determineLetterGrade(59));
    }
    @Test
    void sixtyNineShouldReturnD() throws IllegalAccessException {
        var grade = new DetermineLetterGrade();
        assertEquals('D',grade.determineLetterGrade(69));
    }
    @Test
    void seventyNineShouldReturnC() throws IllegalAccessException {
        var grade = new DetermineLetterGrade();
        assertEquals('C',grade.determineLetterGrade(79));
    }
    @Test
    void eightyNineShouldReturnB() throws IllegalAccessException {
        var grade = new DetermineLetterGrade();
        assertEquals('B',grade.determineLetterGrade(89));
    }
    @Test
    void ninetyNineShouldReturnA() throws IllegalAccessException {
        var grade = new DetermineLetterGrade();
        assertEquals('A',grade.determineLetterGrade(99));
    }

    @Test
    void zeroIsIllegal() throws IllegalAccessException {
        var grade = new DetermineLetterGrade();
        assertThrows(IllegalAccessException.class,()->{ grade.determineLetterGrade(-1);
        });
    }
}