package ar.com.kfgodel.associative.identification.api.config;

import ar.com.kfgodel.associative.identification.impl.conditional.ConditionalMap;

/**
 * This type represents the configuration needed to create interpretations out of entities
 * Created by kfgodel on 13/05/15.
 */
public interface InterpretationConfiguration {
    /**
     * @return The configuration of interpreter per condition
     */
    ConditionalMap<Object,Interpreter> interpreters();

    /**
     * @return The configuration of analyzer per condition
     */
    ConditionalMap<Object, Analyzer> analyzers();

    /**
     * @return The configuration of analyzer per condition
     */
    ConditionalMap<Object, Synthesizer> synthesizers();

}
