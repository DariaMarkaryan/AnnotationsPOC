package com.tests;

import com.utils.ComplexTest;
import com.utils.Step;
import com.utils.TestDescription;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class MyTest extends ComplexTest {

    private static String suiteName = "com.tests.MyTest";

    @BeforeAll
    public static void init(){

    }

    @TestDescription({
        @Step(id = 1, description = "Step 1"),
        @Step(id = 2, description = "Step 2"),
        @Step(id = 3, description = "Step 3")
    })
    @Test
    public void firstTest() {
        testStarted();
        //some complex things
        markPassed(1);
        //sorting valuable values
        markPassed(2);
        //preparing for success
        markPassed(3);
    }

    @TestDescription({
        @Step(id = 1, description = "la"),
        @Step(id = 2, description = "la la"),
        @Step(id = 3, description = "halo"),
        @Step(id = 4, description = "doin something"),
        @Step(id = 5, description = "almost all")
    })
    @Test
    public void testSomething() {
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

    static void testStarted(){
        testStarted(suiteName, Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    static void markPassed(int stepId){
        markPassed(stepId, suiteName, Thread.currentThread().getStackTrace()[2].getMethodName());
    }

}
