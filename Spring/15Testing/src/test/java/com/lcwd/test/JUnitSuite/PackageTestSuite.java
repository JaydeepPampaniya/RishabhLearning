package com.lcwd.test.JUnitSuite;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
//A test suite in JUnit allows you to run multiple test classes together. In JUnit 5, we use the @Suite annotation to group test classes into a suite.
@Suite
@SelectPackages("com.lcwd.test") // Runs all tests in this package
public class PackageTestSuite {
}

