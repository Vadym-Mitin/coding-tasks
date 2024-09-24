package com.example.script.runner.services.impl;

import com.example.script.runner.model.VulnerabilityScript;
import com.example.script.runner.services.ScriptRepository;

import java.util.List;

public class MockScriptRepository implements ScriptRepository  {
    @Override
    public List<VulnerabilityScript> requestScripts() {
        return List.of();
    }
}
