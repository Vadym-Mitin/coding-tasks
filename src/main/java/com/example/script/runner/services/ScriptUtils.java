package com.example.script.runner.services;

import com.example.script.runner.model.VulnerabilityScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ScriptUtils {

    /**
     * Each script has an arbitrary number of dependencies, expressed as a list of script IDs that need to be executed
     * before the given script. There are no circular dependencies, ensuring that the execution plan can be created
     * in a sane order.
     *
     * @param scripts a list of {@link VulnerabilityScript}
     * @return a list of script IDs in the order they should be executed, where dependencies are executed before
     * the dependent script
     */
    public List<Integer> computeScriptsOrder(List<VulnerabilityScript> scripts) {
        List<Integer> result = new ArrayList<>();

        for (VulnerabilityScript script : scripts) {

            Optional.ofNullable(script.dependencies())
                    .ifPresent(result::addAll);

            result.add(script.scriptId());
        }

        return result;
    }

}
