package com.tests;

import com.utils.Step;
import com.utils.TestDescription;
import com.utils.TestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SecondTestSuite {
    @TestDescription({
        @Step(id = 1, description = "Relaxing"),
        @Step(id = 2, description = "Working"),
        @Step(id = 3, description = "Take a rest")
    })
    @Test
    public void doImportantThings() throws NoSuchMethodException, ClassNotFoundException {
        TestUtils.testStarted();
        //Relaxing
        TestUtils.markPassed(1);
        //Relaxing
        TestUtils.markPassed(2);
        //Relaxing
        TestUtils.markPassed(3);
        //Done
    }

    @TestDescription({
        @Step(id = 1, description = "Connect to db"),
        @Step(id = 2, description = "Disconnecting"),
    })
    @Test
    public void connectToDB() throws NoSuchMethodException, ClassNotFoundException {
        TestUtils.testStarted();
        //Connecting to the database
        Assertions.fail();
        TestUtils.markPassed(1);
        //Disconnecting
        TestUtils.markPassed(2);
    }

    @AfterAll
    static void writeToJSON() throws IOException {
        TestUtils.writeMapToJSON();
    }
}
