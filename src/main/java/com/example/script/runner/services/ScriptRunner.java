package com.example.script.runner.services;

import com.example.script.runner.model.VulnerabilityScript;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ScriptRunner {

    @Autowired
    private final ScriptUtils scriptUtils;

    @Autowired
    private final ExecutionService executionService;

    @Autowired
    private final ScriptRepository repository;

    public void runScripts() {
        List<VulnerabilityScript> scripts = repository.requestScripts();
        List<Integer> scriptsOrder = scriptUtils.computeScriptsOrder(scripts);
        executionService.execute(scriptsOrder);
    }


}
