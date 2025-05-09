package com.luv2code.junitdemo;

import jdk.jfr.Enabled;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
class DemoUtilsTest {
    DemoUtils demoUtils;
    @Test
//    @DisplayName("this test for check equals and not equals")
    @Order(-1)
//    @Disabled("Don't run until code is clean")
//    @EnabledOnOs(OS.WINDOWS)
    @EnabledOnOs({OS.WINDOWS,OS.MAC})
//    @EnabledOnJre(JRE.JAVA_8)
//    @EnabledForJreRange(min=JRE.JAVA_10,max=JRE.JAVA_25)
    @EnabledIfEnvironmentVariable(named="JAVA_SETUP",matches = "DEV")
    void testEqualAndNotEquals(){
        System.out.println("test for testEqualAndNotEquals");

        assertEquals(6,demoUtils.add(2,4),"2 + 4 must not be 6");
        assertNotEquals(6,demoUtils.add(1,9),"1+9 must not be 6");
    }
    @Test
    @Order(1)
    void testNullAndNotNull(){
        System.out.println("test for testNullAndNotNull");
        assertNull(demoUtils.checkNull(null),"Object should be null");
        assertNotNull(demoUtils.checkNull("not null"),"Object should not be null");
    }

    @DisplayName("testing for same and not same assertion")
    @Test
    @Order(3)
    void sameAndNotSameAssertion(){
        String str = "testing for sameAndNotSameAssertion";
        assertSame(demoUtils.getAcademy(),demoUtils.getAcademyDuplicate());
        assertNotSame(str, demoUtils.getAcademy());
    }

    @DisplayName("testing for True And False")
    @Test
    @Order(2)
    void testTrueAndFalse(){
        int gradeOne = 10;
        int gradeTwo = 5;

        assertTrue(demoUtils.isGreater(gradeOne,gradeTwo),"This should return true");
        assertFalse(demoUtils.isGreater(gradeTwo,gradeOne),"This should return false");
    }

    @DisplayName("Test for array")
    @Test
    void testArrayIsEqual(){
        String[] alphabet = {"A","B","C"};
        assertArrayEquals(alphabet,demoUtils.getFirstThreeLettersOfAlphabet(),"Array should same");
    }

    @DisplayName("Test for List")
    @Test
    void testListIsEqual(){
        List<String> list=List.of("luv", "2", "code");
        assertIterableEquals(list, demoUtils.getAcademyInList(),"List should same");
    }

    @DisplayName("Test for Lines")
    @Test
    void testMathLine(){
        List<String> list=List.of("luv", "2", "code");
        assertLinesMatch(list, demoUtils.getAcademyInList(),"Line should same");
        assertLinesMatch("list".lines(), "list".lines(),"Line should same");
    }

    @DisplayName("Throws and Does Not Throw")
    @Test
    void testThrowAndDoesNotThrow(){
        assertThrows(Exception.class,()->{demoUtils.throwException(-1);},"Should throw exception");
        assertDoesNotThrow(()->{demoUtils.throwException(1);},"Should not throw exception");
    }

    @DisplayName("Timeout")
    @Test
    void testTimeOut() throws InterruptedException {
       assertTimeoutPreemptively(Duration.ofSeconds(3),()->{demoUtils.checkTimeout();});
       Thread.sleep(5000);
        System.out.println("Sleeping over");
    }

    @BeforeEach
    void testForBeforeEach(){
        demoUtils = new DemoUtils();
        System.out.println("test for testForBeforeEach");
    }
    @AfterEach
    void testForAfterEach(){
        System.out.println("test for testForAfterEach");
    }
    @BeforeAll
    static void testForBeforeAll(){
        System.out.println("test for testForBeforeAll");
    }
    @AfterAll
    static void testForAfterAll(){
        System.out.println("test for testForAfterAll");
    }
}