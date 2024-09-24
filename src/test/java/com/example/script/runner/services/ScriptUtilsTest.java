package com.example.script.runner.services;

import com.example.script.runner.model.VulnerabilityScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
class ScriptUtilsTest {

    public List<VulnerabilityScript> correctList;


    @BeforeAll
    public void initScripts() {
        correctList = List.of(
                new VulnerabilityScript(1, List.of(2, 3, 4)),
                new VulnerabilityScript(5, List.of(6)),
                new VulnerabilityScript(7, List.of()));


    }

    @Test
    public void shouldComputeRightScriptOrder() {
        ScriptUtils scriptUtils = new ScriptUtils();

        List<Integer> expected = List.of(2, 3, 4, 1, 6, 5, 7);

        List<Integer> actual = scriptUtils.computeScriptsOrder(correctList);

        System.out.println("***********======= expected: ");
        System.out.println(expected);
        System.out.println("***********======= actual: ");
        System.out.println(actual);

        Assertions.assertEquals(expected, actual);
    }
}