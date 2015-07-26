package ar.com.kfgodel.associative.impl.config;

import ar.com.kfgodel.associative.impl.interpreter.UninterpretableIntepreter;

/**
 * This type represents a minimal configuration as an extension reference
 * Created by kfgodel on 19/05/2015.
 */
public class MinimalConfiguration extends ConfigurationSupport {

    public static MinimalConfiguration create() {
        MinimalConfiguration configuration = new MinimalConfiguration();
        configuration.initialize();
        return configuration;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.interpreters().bind(Object.class::isInstance, UninterpretableIntepreter.create());
    }
}
