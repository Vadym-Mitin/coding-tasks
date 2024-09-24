package com.example.script.runner.services;

import com.example.script.runner.model.VulnerabilityScript;

import java.util.List;

public interface ScriptRepository {

    List<VulnerabilityScript> requestScripts();
}
