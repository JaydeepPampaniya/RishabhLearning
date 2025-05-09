package com.lcwd.test.services;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CalculatorServiceTest {
    int counter = 0;
    @Test
    @DisplayName("This is addTwoNumbers test cases")
    void addTwoNumbers() {
        System.out.println("test for addTwoNumbers");
        for(int i=1; i<=20; i++){
            counter +=i;
        }
        System.out.println("counter int first test" + counter);
        int result = CalculatorService.addTwoNumbers(12,45);
         assertEquals(57,result,"test add Two Numbers failed");
    }

    @Test
    @Disabled
    void productTwoNumbers() {
        System.out.println("test for productTwoNumbers");
        int result = CalculatorService.productTwoNumbers(12,45);
        assertEquals(540,result,"test productTwoNumbers failed");
    }

    @Test
    void devideTwoNumbers() {
    }

    @Test //(timeout = 1000)//in milliseconds junit 5 does't support timeout
    void sumAnyNumbers() throws InterruptedException {
        System.out.println("test for sumAnyNumbers");
        for(int i=1; i<=100; i++){
            counter +=i;
        }
        Thread.sleep(2000);
        System.out.println("counter int second test" + counter);
        int result = CalculatorService.sumAnyNumbers(12,45,3,2,1,2);
        assertEquals(65,result, "test umAnyNumbers failed");
    }

    @BeforeAll
    static void init(){
        System.out.println("@BeforeClass run before all the unit testing runs");
        System.out.println("Started test : "+ new Date().toString());
        //connection open / File open
    }
    @BeforeEach //executes the method before each test case and before @BeforeClass
    public void beforeEach(){
        counter=0;
        System.out.println("Before each test case");
    }
    @AfterEach //executes the method after each test case and before @AfterClass
    public void afterEach(){
        System.out.println("After each test case");
    }
    @AfterAll
    public static void cleanUp(){
        System.out.println("after class");
        System.out.println("End test : "+ new Date().toString());
        // mostly useful for close the connections and flle
    }

//    @Nested for nested class or method
//    @Tag()
//    @TestFactory

    @Test
    public void testing(){
//        System.out.println("Testing some assertion methods");
//        float actual = 12;
//        Float expected = 12.0f;
//        assertEquals(expected,actual);
//
//        int[] actualArr = {1,2,3,4,5,1};
//        int[] expectedArr = {1,2,3,4,5,2};
//        assertArrayEquals(expectedArr,actualArr);

        //for object
        String name = new String("jaydeep");
        String surname = new String("jaydeep");
//        assertSame(surname,name); this one is false because different memory locations
        assertEquals(surname,name);

//        assertNull();
//        assertNotNull();
//        assertTrue();
//        assertFalse();
//        assertNotEquals();

        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = Arrays.asList(1,2,3,5,4);
        assertIterableEquals(list2,list1);

        assertThrows(RuntimeException.class,()->{
            throw new RuntimeException(("This is testing exceptions"));
        });
    }
}