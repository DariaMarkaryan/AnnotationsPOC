package com.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public abstract class ComplexTest {
    static private Map<String, Map<String, Map<String, String>>> testResults = new HashMap<>();

    protected static void markPassed(int stepId) throws IOException, ClassNotFoundException, NoSuchMethodException {
        String classCallerName = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodCallerName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String dynamicInfoString = Files.readString(new File("test_results.json").toPath(), StandardCharsets.UTF_8);
        Map<String, Map<String, Map<String, String>>> results = new Gson().fromJson(dynamicInfoString, new TypeToken<Map<String, Map<String, Map<String, String>>>>() {
        }.getType());
        TestDescription description = Class.forName(classCallerName).getMethod(methodCallerName).getAnnotation(TestDescription.class);
        Step[] steps = description.value();
        results.get(classCallerName).get(methodCallerName).put(steps[stepId-1].description(), "passed");
        String gsonString = new Gson().toJson(results, new TypeToken<Map<String, Map<String, Map<Integer, String>>>>() {
        }.getType());
        try (FileWriter file = new FileWriter("test_results.json")) {
            file.write(gsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void testStarted() throws NoSuchMethodException, ClassNotFoundException, IOException {
        writeStepsInMap(Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getMethodName());
        writeMapToJSON(Thread.currentThread().getStackTrace()[2].getClassName(), Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    private static void writeMapToJSON(String className, String testName) throws IOException {
        String dynamicInfoString = Files.readString(new File("test_results.json").toPath(), StandardCharsets.UTF_8);
        Map<String, Map<String, Map<String, String>>> oldData = new Gson().fromJson(dynamicInfoString, new TypeToken<Map<String, Map<String, Map<String, String>>>>() {
        }.getType());
        Map<String, Map<String, Map<String, String>>> mergedData = new HashMap<>(oldData);
        Map<String, Map<String, String>> keyValue = oldData.get(className);
        keyValue.put(testName, testResults.get(className).get(testName));
        mergedData.put(className, keyValue);
        String gsonString = new Gson().toJson(mergedData, new TypeToken<Map<String, Map<String, Map<Integer, String>>>>() {
        }.getType());
        try (FileWriter file = new FileWriter("test_results.json")) {
            file.write(gsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeStepsInMap(String className, String testName) throws ClassNotFoundException, NoSuchMethodException {
        TestDescription description = Class.forName(className).getMethod(testName).getAnnotation(TestDescription.class);
        Step[] steps = description.value();
        Map<String, String> stepsInfo = new HashMap<>();
        for (Step s : steps)
            stepsInfo.put(s.description(), "failed");
        testResults.put(className, new HashMap<>() {{
            put(testName, stepsInfo);
        }});
    }
}