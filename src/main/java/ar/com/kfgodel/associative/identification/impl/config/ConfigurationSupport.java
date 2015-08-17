package ar.com.kfgodel.associative.identification.impl.config;

import ar.com.kfgodel.associative.identification.api.config.Analyzer;
import ar.com.kfgodel.associative.identification.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.identification.api.config.Interpreter;
import ar.com.kfgodel.associative.identification.api.config.Synthesizer;
import ar.com.kfgodel.associative.identification.impl.conditional.ConditionalMap;
import ar.com.kfgodel.associative.identification.impl.conditional.ConditionalMapImpl;
import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.materialization.api.config.MaterializationConfiguration;
import ar.com.kfgodel.associative.materialization.api.config.Materializer;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.associative.persistence.impl.magi.MagiRepoImpl;

/**
 * This type serves as a base class to extend for configuration types
 * Created by kfgodel on 19/05/2015.
 */
public class ConfigurationSupport implements InterpretationConfiguration, MaterializationConfiguration {

    private ConditionalMap<Object, Interpreter> interpreterConfig;
    private ConditionalMap<Object, Analyzer> analyzerConfig;
    private ConditionalMap<Object,Synthesizer> synthesizerConfig;
    private ConditionalMap<ExpectedObjectDefinition,Materializer> materializersConfig;
    private MagiRepo magiRepo;

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
        this.materializersConfig = ConditionalMapImpl.create();
        this.magiRepo = MagiRepoImpl.create();
    }

    @Override
    public ConditionalMap<ExpectedObjectDefinition, Materializer> materializers() {
        return materializersConfig;
    }

    @Override
    public MagiRepo getMagiRepository() {
        return magiRepo;
    }
}
