package ar.com.kfgodel.associative.impl.config;

import ar.com.kfgodel.associative.api.config.Analyzer;
import ar.com.kfgodel.associative.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.api.config.Interpreter;
import ar.com.kfgodel.associative.api.config.Synthesizer;
import ar.com.kfgodel.associative.impl.analyzer.BoundFieldAnalyzer;
import ar.com.kfgodel.associative.impl.analyzer.UnknownTypeAnalyzer;
import ar.com.kfgodel.associative.impl.conditional.ConditionalMap;
import ar.com.kfgodel.associative.impl.conditional.ConditionalMapImpl;
import ar.com.kfgodel.associative.impl.inter.ConceptInterpreter;
import ar.com.kfgodel.associative.impl.synthesizer.BoundFieldSynthesizer;
import ar.com.kfgodel.associative.impl.synthesizer.UnknownTypeSynthesizer;
import ar.com.kfgodel.diamond.api.fields.BoundField;

/**
 * Created by kfgodel on 13/05/15.
 */
public class DefaultConfiguration implements InterpretationConfiguration {


    private ConditionalMap<Object, Interpreter> interpreterConfig;
    private ConditionalMap<Object, Analyzer> analyzerConfig;
    private ConditionalMap<Object,Synthesizer> synthesizerConfig;


    @Override
    public ConditionalMap<Object, Interpreter> getInterpreterConfiguration() {
        return interpreterConfig;
    }

    @Override
    public ConditionalMap<Object, Analyzer> getAnalyzerConfiguration() {
        return analyzerConfig;
    }

    @Override
    public ConditionalMap<Object, Synthesizer> getSynthesizerConfiguration() {
        return synthesizerConfig;
    }


    public static DefaultConfiguration create() {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.interpreterConfig = ConditionalMapImpl.create();
        configuration.analyzerConfig = ConditionalMapImpl.create();
        configuration.synthesizerConfig = ConditionalMapImpl.create();
        configuration.initialize();
        return configuration;
    }

    private void initialize() {
        this.interpreterConfig.bind(Object.class::isInstance, ConceptInterpreter.create());

        this.analyzerConfig.bind(BoundField.class::isInstance, BoundFieldAnalyzer.create());
        this.analyzerConfig.bind(Object.class::isInstance, UnknownTypeAnalyzer.create());

        this.synthesizerConfig.bind(BoundField.class::isInstance, BoundFieldSynthesizer.create());
        this.synthesizerConfig.bind(Object.class::isInstance, UnknownTypeSynthesizer.create());
    }

}
