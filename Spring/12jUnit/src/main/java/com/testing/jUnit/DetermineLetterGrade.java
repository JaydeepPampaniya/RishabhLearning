package com.testing.jUnit;

public class DetermineLetterGrade {
    public char determineLetterGrade(int numberGrade) throws IllegalAccessException {
        if(numberGrade <0){
            throw new IllegalAccessException("Number grade cannot be 0");
        }
        if(numberGrade<60){
            return 'F';
        }
        if(numberGrade<70){
            return 'D';
        }
        if(numberGrade <80){
            return 'C';
        }
        if(numberGrade <90){
            return 'B';
        }
        return 'A';
    }
}
