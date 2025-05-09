package com.lcwd.test.services;

import org.junit.*;

import java.util.Date;

public class CalculatorServiceTest {
    int counter = 0;
    @Test
    public void addTwoNumbers() {
        System.out.println("test for addTwoNumbers");
        for(int i=1; i<=20; i++){
            counter +=i;
        }
        System.out.println("counter int first test" + counter);
        int result = CalculatorService.addTwoNumbers(12,45);
        Assert.assertEquals(57,result);
    }

    @Test
    public void productTwoNumbers() {
        System.out.println("test for productTwoNumbers");
        int result = CalculatorService.productTwoNumbers(12,45);
        Assert.assertEquals(540,result);
    }

    @Test
    public void devideTwoNumbers() {
    }

    @Test(timeout = 1000)//in milliseconds
    public void sumAnyNumbers() throws InterruptedException {
        System.out.println("test for sumAnyNumbers");
        for(int i=1; i<=100; i++){
            counter +=i;
        }
        Thread.sleep(2000);
        System.out.println("counter int second test" + counter);
        int result = CalculatorService.sumAnyNumbers(12,45,3,2,1,2);
        Assert.assertEquals(65,result);
    }

    @BeforeClass
    public static void init(){
        System.out.println("@BeforeClass run before all the unit testing runs");
        System.out.println("Started test : "+ new Date().toString());
        //connection open / File open
    }
    @Before //executes the method before each test case and before @BeforeClass
    public void beforeEach(){
        counter=0;
        System.out.println("Before each test case");
    }
    @After //executes the method after each test case and before @AfterClass
    public void afterEach(){
        System.out.println("After each test case");
    }
    @AfterClass
    public static void cleanUp(){
        System.out.println("after class");
        System.out.println("End test : "+ new Date().toString());
        // mostly useful for close the connections and flle
    }
}