package com.lcwd.test.JUnitSuite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ MathUtilsTest.class, StringUtilsTest.class }) // Add multiple test classes
public class TestSuite {
    // No need to write any methods, this will run both test classes
}
