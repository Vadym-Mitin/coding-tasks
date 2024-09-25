package com.example.script.runner;

import com.example.script.runner.model.VulnerabilityScript;
import com.example.script.runner.services.ExecutionService;
import com.example.script.runner.services.ScriptRepository;
import com.example.script.runner.utils.ScriptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
