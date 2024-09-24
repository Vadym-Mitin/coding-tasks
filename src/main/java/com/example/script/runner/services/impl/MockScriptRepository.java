package com.example.script.runner.services.impl;

import com.example.script.runner.model.VulnerabilityScript;
import com.example.script.runner.services.ScriptRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockScriptRepository implements ScriptRepository  {
    @Override
    public List<VulnerabilityScript> requestScripts() {
        return List.of();
    }
}
