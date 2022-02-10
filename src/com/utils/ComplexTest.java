package com.utils;

import java.util.Map;

public abstract class ComplexTest {
    static private Map<String, Map<String, Map<String, String>>> testResults;

    protected static void markPassed(int stepId, String className, String testName) {
    }

    protected static void testStarted(String className, String testName) {
        writeStepsInMap(className, testName);
    }

    private static void writeStepsInMap(String className, String testName) {

    }

    void isTestPassed() {
    }
}
