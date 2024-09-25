package com.example.script.runner.utils;

import com.example.script.runner.model.VulnerabilityScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
class ScriptUtilsTest {

    private final ScriptUtils scriptUtils = new ScriptUtils();
    public List<VulnerabilityScript> correctList;
    public List<VulnerabilityScript> containsDuplicates;


    @BeforeAll
    public void initScripts() {
        correctList = List.of(
                new VulnerabilityScript(1, List.of(2, 3)),
                new VulnerabilityScript(4, List.of(5)),
                new VulnerabilityScript(6, EMPTY_LIST)
        );

        containsDuplicates = List.of(
                new VulnerabilityScript(1, List.of(2, 3)),
                new VulnerabilityScript(4, List.of(5)),
                new VulnerabilityScript(6, EMPTY_LIST),
                new VulnerabilityScript(3, null),
                new VulnerabilityScript(2, List.of(5))
        );

    }

    @Test
    public void shouldComputeRightScriptOrder() {
        List<Integer> actual = scriptUtils.computeScriptsOrder(correctList);

        List<Integer> expected = List.of(2, 3, 1, 5, 4, 6);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldComputeRightScriptOrderWithDuplicates() {
        List<Integer> actual = scriptUtils.computeScriptsOrder(containsDuplicates);

        List<Integer> expected = List.of(2, 3, 1, 5, 4, 6, 3, 5, 2);
        assertEquals(expected, actual);
    }


    @Test
    public void handleNullableScripList() {
        List<Integer> scriptsOrder = scriptUtils.computeScriptsOrder(null);
        assertEquals(EMPTY_LIST, scriptsOrder);
    }
}