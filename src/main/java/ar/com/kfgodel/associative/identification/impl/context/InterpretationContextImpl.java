package ar.com.kfgodel.associative.identification.impl.context;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.config.Analyzer;
import ar.com.kfgodel.associative.identification.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.identification.api.config.Interpreter;
import ar.com.kfgodel.associative.identification.api.config.Synthesizer;
import ar.com.kfgodel.associative.identification.api.context.InterpretationContext;
import ar.com.kfgodel.associative.identification.api.exceptions.InterpretationException;
import ar.com.kfgodel.associative.identification.impl.generator.IdentityGenerator;
import ar.com.kfgodel.associative.identification.impl.generator.IdentityGeneratorImpl;
import ar.com.kfgodel.associative.identification.impl.interpretation.EntityRepresentationImpl;
import ar.com.kfgodel.associative.identification.impl.maps.EntityMap;
import ar.com.kfgodel.associative.identification.impl.maps.EntityMapImpl;
import ar.com.kfgodel.associative.identification.impl.maps.RepresentationMap;
import ar.com.kfgodel.associative.identification.impl.maps.RepresentationMapImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.optionals.Optional;

/**
 * This type is the actual implementation of an interpretation context
 * Created by kfgodel on 13/05/15.
 */
public class InterpretationContextImpl implements InterpretationContext {

    private InterpretationConfiguration config;
    private EntityMap identityPerEntity;
    private IdentityGenerator generator;
    private RepresentationMap representationPerIdentity;

    public static InterpretationContextImpl create(InterpretationConfiguration config) {
        InterpretationContextImpl context = new InterpretationContextImpl();
        context.config = config;
        context.identityPerEntity = EntityMapImpl.create();
        context.generator = IdentityGeneratorImpl.create();
        context.representationPerIdentity = RepresentationMapImpl.create();
        return context;
    }

    @Override
    public DecomposableTask getBestInterpretationProcessFor(Object entity) {
        return getBestInterpreterFor(entity).describeProcessFor(entity, this);
    }

    @Override
    public EntityRepresentation completeRepresentation() {
        return EntityRepresentationImpl.create(identityPerEntity, representationPerIdentity);
    }

    private Interpreter getBestInterpreterFor(Object entity) {
        Optional<Interpreter> interpreter = config.interpreters().getFirstMatchingFor(entity);
        return interpreter.orElseThrow(() -> new InterpretationException("There's no suitable interpreter for entity: " + entity));
    }

    @Override
    public Analyzer getBestAnalyzerFor(Object entity) {
        Optional<Analyzer> analyzer = config.analyzers().getFirstMatchingFor(entity);
        return analyzer.orElseThrow(()-> new InterpretationException("There's no suitable analyzer for entity: " + entity));
    }

    @Override
    public Synthesizer getBestSynthesizerFor(Object entity) {
        Optional<Synthesizer> synthesizer = config.synthesizers().getFirstMatchingFor(entity);
        return synthesizer.orElseThrow(() -> new InterpretationException("There's no suitable synthesizer for entity: " + entity));
    }

    @Override
    public Identity createIdentityFor(Object entity) {
        Identity newIdentity = generator.createIdentity();
        identityPerEntity.put(entity, newIdentity);
        return newIdentity;
    }

    @Override
    public void storeRepresentationFor(Identity entityIdentity, Object entityRepresentation) {
        representationPerIdentity.put(entityIdentity, entityRepresentation);
    }

    @Override
    public Optional<Identity> getIdentityFor(Object entity) {
        return identityPerEntity.getIdentityFor(entity);
    }

}
