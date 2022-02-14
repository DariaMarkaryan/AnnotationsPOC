package com.tests;

import com.utils.Step;
import com.utils.TestDescription;
import com.utils.TestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MyTests {
    @TestDescription({
        @Step(id = 1, description = "la"),
        @Step(id = 2, description = "la la"),
        @Step(id = 3, description = "halo")
    })
    @Test
    public void testSomething() throws NoSuchMethodException, ClassNotFoundException {
        TestUtils.testStarted();
        TestUtils.markPassed(1);
        TestUtils.markPassed(2);
        TestUtils.markPassed(3);
    }

    @TestDescription({
        @Step(id = 1, description = "Step1"),
        @Step(id = 2, description = "step 2"),
        @Step(id = 3, description = "Step 3"),
        @Step(id = 4, description = "Step 4"),
        @Step(id = 5, description = "done done done")
    })
    @Test
    public void anotherTest() throws NoSuchMethodException, ClassNotFoundException {
        TestUtils.testStarted();
        TestUtils.markPassed(1);
        TestUtils.markPassed(2);
        TestUtils.markPassed(3);
        Assertions.fail();
        TestUtils.markPassed(4);
        TestUtils.markPassed(5);
    }

    @AfterAll
    static void writeToJSON() throws IOException {
        TestUtils.writeMapToJSON();
    }
}
