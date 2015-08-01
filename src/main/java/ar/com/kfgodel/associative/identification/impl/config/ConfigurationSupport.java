package ar.com.kfgodel.associative.identification.impl.config;

import ar.com.kfgodel.associative.identification.api.config.Analyzer;
import ar.com.kfgodel.associative.identification.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.identification.api.config.Interpreter;
import ar.com.kfgodel.associative.identification.api.config.Synthesizer;
import ar.com.kfgodel.associative.identification.impl.conditional.ConditionalMap;
import ar.com.kfgodel.associative.identification.impl.conditional.ConditionalMapImpl;

/**
 * This type serves as a base class to extend for configuration types
 * Created by kfgodel on 19/05/2015.
 */
public class ConfigurationSupport implements InterpretationConfiguration {

    private ConditionalMap<Object, Interpreter> interpreterConfig;
    private ConditionalMap<Object, Analyzer> analyzerConfig;
    private ConditionalMap<Object,Synthesizer> synthesizerConfig;

    @Override
    public ConditionalMap<Object, Interpreter> interpreters() {
        return interpreterConfig;
    }

    @Override
    public ConditionalMap<Object, Analyzer> analyzers() {
        return analyzerConfig;
    }

    @Override
    public ConditionalMap<Object, Synthesizer> synthesizers() {
        return synthesizerConfig;
    }

    protected void initialize() {
        this.interpreterConfig = ConditionalMapImpl.create();
        this.analyzerConfig = ConditionalMapImpl.create();
        this.synthesizerConfig = ConditionalMapImpl.create();
    }
}
