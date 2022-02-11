package com.tests;

import com.utils.ComplexTest;
import com.utils.Step;
import com.utils.TestDescription;
import org.junit.jupiter.api.*;

import java.io.IOException;


public class MyTest extends ComplexTest {

    @TestDescription({
        @Step(id = 1, description = "la"),
        @Step(id = 2, description = "la la"),
        @Step(id = 3, description = "halo"),
        @Step(id = 4, description = "doin something"),
        @Step(id = 5, description = "almost all")
    })
    @Test
    public void testSomething() throws NoSuchMethodException, ClassNotFoundException, IOException {
        testStarted();
        //some complex things
        markPassed(1);
        //sorting valuable values
        markPassed(2);
        //
        markPassed(3);
        //
        markPassed(4);
        //
        markPassed(5);
        //DONE
    }

    @TestDescription({
        @Step(id = 1, description = "connect"),
        @Step(id = 2, description = "expand"),
        @Step(id = 3, description = "close connection")
    })
    @Test
    public void anotherTest() throws NoSuchMethodException, ClassNotFoundException, IOException {
        testStarted();
        //some complex things
        markPassed(1);
        //sorting valuable values
        markPassed(2);
        //
        Assertions.fail();
        System.out.println("im here");
        markPassed(3);
        //DONE
    }
}
