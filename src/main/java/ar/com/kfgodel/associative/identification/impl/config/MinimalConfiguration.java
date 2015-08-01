package ar.com.kfgodel.associative.identification.impl.config;

import ar.com.kfgodel.associative.identification.impl.interpreter.UninterpretableIntepreter;

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
        // Use the uninterpretable interpreter for everything
        this.interpreters().bind((any)-> true, UninterpretableIntepreter.create());
    }
}
