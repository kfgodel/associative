package ar.com.kfgodel.associative.identification.impl.config;

import ar.com.kfgodel.associative.identification.api.unknown.UninterpretableRepresentation;
import ar.com.kfgodel.associative.identification.impl.analyzer.BoundFieldAnalyzer;
import ar.com.kfgodel.associative.identification.impl.analyzer.UnknownTypeAnalyzer;
import ar.com.kfgodel.associative.identification.impl.interpreter.ConceptInterpreter;
import ar.com.kfgodel.associative.identification.impl.interpreter.PerceptInterpreter;
import ar.com.kfgodel.associative.identification.impl.interpreter.UninterpretableIntepreter;
import ar.com.kfgodel.associative.identification.impl.synthesizer.BoundFieldSynthesizer;
import ar.com.kfgodel.associative.identification.impl.synthesizer.UnknownTypeSynthesizer;
import ar.com.kfgodel.associative.materialization.impl.materializers.PerceptMaterializer;
import ar.com.kfgodel.diamond.api.fields.BoundField;

import java.util.Objects;

/**
 * Created by kfgodel on 13/05/15.
 */
public class DefaultConfiguration extends ConfigurationSupport {


    public static DefaultConfiguration create() {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.initialize();
        return configuration;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.interpreters().bind(UninterpretableRepresentation.class::isInstance, UninterpretableIntepreter.create());
        this.interpreters().bind(Objects::isNull, UninterpretableIntepreter.create());
        this.interpreters().bind(String.class::isInstance, PerceptInterpreter.create());
        this.interpreters().bind(Object.class::isInstance, ConceptInterpreter.create());

        this.analyzers().bind(BoundField.class::isInstance, BoundFieldAnalyzer.create());
        this.analyzers().bind(Object.class::isInstance, UnknownTypeAnalyzer.create());

        this.synthesizers().bind(BoundField.class::isInstance, BoundFieldSynthesizer.create());
        this.synthesizers().bind(Object.class::isInstance, UnknownTypeSynthesizer.create());

        this.materializers().bind((definition -> String.class.isAssignableFrom(definition.expectedClass())),
                PerceptMaterializer.create());
    }
}
