package com.example.script.runner.services;

import com.example.script.runner.model.VulnerabilityScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScriptRunnerTest {

    private ExecutionService executionServiceMock;
    private ScriptRepository repositoryMock;
    private ScriptRunner scriptRunner;
    private final ScriptUtils scriptUtils = new ScriptUtils();

    @BeforeEach
    public void init() {
        executionServiceMock = mock(ExecutionService.class);
        repositoryMock = mock(ScriptRepository.class);
        scriptRunner = new ScriptRunner(scriptUtils, executionServiceMock, repositoryMock);
    }


    @Test
    public void shouldRequestScriptsAndExecuteInProperOrder() {
        List<VulnerabilityScript> scripts = List.of(
                new VulnerabilityScript(1, List.of(2, 3, 4)),
                new VulnerabilityScript(5, List.of(6)),
                new VulnerabilityScript(7, List.of())
        );

        doReturn(scripts).when(repositoryMock).requestScripts();

        scriptRunner.runScripts();

        List<Integer> expectedOrder = List.of(2, 3, 4, 1, 6, 5, 7);

        verify(executionServiceMock, times(1)).execute(expectedOrder);
    }


    @Test
    public void rethrowAnException() {
        doReturn(List.of()).when(repositoryMock).requestScripts();

        String exceptionMessage = "some problems";
        doThrow(new RuntimeException(exceptionMessage)).when(executionServiceMock).execute(anyList());


        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> scriptRunner.runScripts());

        assertEquals(exceptionMessage, runtimeException.getMessage());
    }

}