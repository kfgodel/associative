package ar.com.kfgodel.associative.impl.context;

import ar.com.kfgodel.associative.api.EntityInterpretation;
import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.config.Analyzer;
import ar.com.kfgodel.associative.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.api.config.Interpreter;
import ar.com.kfgodel.associative.api.config.Synthesizer;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.api.exceptions.InterpretationException;
import ar.com.kfgodel.associative.impl.generator.IdentityGenerator;
import ar.com.kfgodel.associative.impl.generator.IdentityGeneratorImpl;
import ar.com.kfgodel.associative.impl.inter.EntityInterpretationImpl;
import ar.com.kfgodel.associative.impl.maps.EntityMap;
import ar.com.kfgodel.associative.impl.maps.EntityMapImpl;
import ar.com.kfgodel.associative.impl.maps.InterpretationMap;
import ar.com.kfgodel.associative.impl.maps.InterpretationMapImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

import java.util.Optional;

/**
 * This type is the actual implementation of an interpretation context
 * Created by kfgodel on 13/05/15.
 */
public class InterpretationContextImpl implements InterpretationContext {

    private InterpretationConfiguration config;
    private EntityMap identitiesPerEntity;
    private IdentityGenerator generator;
    private InterpretationMap interpretationPerIdentity;

    public static InterpretationContextImpl create(InterpretationConfiguration config) {
        InterpretationContextImpl context = new InterpretationContextImpl();
        context.config = config;
        context.identitiesPerEntity = EntityMapImpl.create();
        context.generator = IdentityGeneratorImpl.create();
        context.interpretationPerIdentity = InterpretationMapImpl.create();
        return context;
    }

    @Override
    public DecomposableTask getBestInterpretationProcessFor(Object entity) {
        Optional<Interpreter> foundInterpreter = config.interpreters().getFirstMatchingFor(entity);
        return foundInterpreter
                .map((interpreter -> interpreter.describeProcessFor(entity, this)))
                .orElseThrow(() -> new InterpretationException("There's no suitable interpreter for entity: " + entity));
    }

    @Override
    public EntityInterpretation getInterpretation() {
        return EntityInterpretationImpl.create(identitiesPerEntity, interpretationPerIdentity);
    }

    @Override
    public Analyzer getBestAnalyzerFor(Object entity) {
        Optional<Analyzer> analyzer = config.analyzers().getFirstMatchingFor(entity);
        return analyzer.orElseThrow(()-> new InterpretationException("There's no suitable analyzer for entity: " + entity));
    }

    @Override
    public Synthesizer getBestSynthesizerFor(Object entity) {
        Optional<Synthesizer> synthesizer = config.synthesizers().getFirstMatchingFor(entity);
        return synthesizer.orElseThrow(()-> new InterpretationException("There's no suitable synthesizer for entity: " + entity));
    }

    @Override
    public Identity getOrCreateIdentityFor(Object entity) {
        return getIdentityFor(entity)
            .orElseGet(()->{
                Identity newIdentity = generator.createIdentity();
                identitiesPerEntity.put(entity, newIdentity);
                return newIdentity;
            });
    }

    @Override
    public void storeInterpretation(Identity entityIdentity, Object entityInterpretation) {
        interpretationPerIdentity.put(entityIdentity, entityInterpretation);
    }

    @Override
    public Optional<Identity> getIdentityFor(Object entity) {
        return identitiesPerEntity.getIdentityFor(entity);
    }

}
