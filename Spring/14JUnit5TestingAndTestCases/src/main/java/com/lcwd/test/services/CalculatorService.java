package com.lcwd.test.services;

public class CalculatorService {
    public static int addTwoNumbers(int a, int b){
    return a+b;
    }
    public static int productTwoNumbers(int a, int b){
        return a*b;
    }
    public static double devideTwoNumbers(int a, int b){
        return (double) a/b;
    }
    public static int sumAnyNumbers(int ...numbers){
        int s=0;
        for(int n:numbers){
            s+=n;
        }
        return s;
    }
}
