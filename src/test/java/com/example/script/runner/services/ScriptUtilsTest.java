package com.example.script.runner.services;

import com.example.script.runner.model.VulnerabilityScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(Lifecycle.PER_CLASS)
class ScriptUtilsTest {

    private final ScriptUtils scriptUtils = new ScriptUtils();
    public List<VulnerabilityScript> correctList;
    public List<VulnerabilityScript> containsDuplicates;


    @BeforeAll
    public void initScripts() {
        correctList = List.of(
                new VulnerabilityScript(1, List.of(2, 3, 4)),
                new VulnerabilityScript(5, List.of(6)),
                new VulnerabilityScript(7, List.of())
        );

        containsDuplicates = List.of(
                new VulnerabilityScript(1, List.of(2, 3, 4)),
                new VulnerabilityScript(5, List.of(6)),
                new VulnerabilityScript(7, List.of()),
                new VulnerabilityScript(3, List.of()),
                new VulnerabilityScript(2, List.of(7))
        );

    }

    @Test
    public void shouldComputeRightScriptOrder() {
        List<Integer> actual = scriptUtils.computeScriptsOrder(correctList);

        List<Integer> expected = List.of(2, 3, 4, 1, 6, 5, 7);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldComputeRightScriptOrderWithDuplicates() {
        List<Integer> actual = scriptUtils.computeScriptsOrder(containsDuplicates);

        List<Integer> expected = List.of(2, 3, 4, 1, 6, 5, 7);
        assertEquals(expected, actual);
    }

    @Test
    public void scriptListMustNotBeNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> scriptUtils.computeScriptsOrder(null));

        assertEquals("The scriptIds list must not be null", exception.getMessage());
    }
}