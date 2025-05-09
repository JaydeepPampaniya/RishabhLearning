package com.lcwd.test.JUnitSuite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {
    @Test
    void testStringContains() {
        assertTrue("Hello JUnit".contains("JUnit"));
    }
}
