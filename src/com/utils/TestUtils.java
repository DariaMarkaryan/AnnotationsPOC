package com.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestUtils {
    static private Map<String, Map<String, TreeMap<Integer, List<String>>>> testResults = new HashMap<>();

    public static void markPassed(int stepId) throws ClassNotFoundException, NoSuchMethodException {
        String classCallerName = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodCallerName = Thread.currentThread().getStackTrace()[2].getMethodName();
        TestDescription description = Class.forName(classCallerName).getMethod(methodCallerName).getAnnotation(TestDescription.class);
        Step[] steps = description.value();
        testResults.get(classCallerName).get(methodCallerName).put(stepId, List.of(steps[stepId - 1].description(), "passed"));
    }

    public static void testStarted() throws NoSuchMethodException, ClassNotFoundException {
        String classCallerName = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodCallerName = Thread.currentThread().getStackTrace()[2].getMethodName();
        //write steps in map
        TestDescription description = Class.forName(classCallerName).getMethod(methodCallerName).getAnnotation(TestDescription.class);
        Step[] steps = description.value();
        TreeMap<Integer, List<String>> treeMap = new TreeMap<>();
        for (Step s : steps)
            treeMap.put(s.id(), List.of(s.description(), "failed"));
        testResults.putIfAbsent(classCallerName, new HashMap<>());
        testResults.get(classCallerName).put(methodCallerName, treeMap);
    }

    public static void writeMapToJSON() throws IOException {
        String classCallerName = Thread.currentThread().getStackTrace()[2].getClassName();
        File f = new File("test_results.json");
        String gsonString;
        if (!f.exists()) {
            f.createNewFile();
            gsonString = new Gson().toJson(testResults, new TypeToken<Map<String, Map<String, TreeMap<Integer, List<String>>>>>() {
            }.getType());
        } else {
            String dynamicInfoString = Files.readString(f.toPath(), StandardCharsets.UTF_8);
            Map<String, Map<String, TreeMap<Integer, List<String>>>> oldData = new Gson().fromJson(dynamicInfoString, new TypeToken<Map<String, Map<String, TreeMap<Integer, List<String>>>>>() {
            }.getType());
            oldData.put(classCallerName, testResults.get(classCallerName));
            gsonString = new Gson().toJson(oldData, new TypeToken<Map<String, Map<String, TreeMap<Integer, List<String>>>>>() {
            }.getType());
        }
        try (FileWriter file = new FileWriter("test_results.json")) {
            file.write(gsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
