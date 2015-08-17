package ar.com.kfgodel.associative.materialization.impl.context;

import ar.com.kfgodel.associative.identification.api.exceptions.InterpretationException;
import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.materialization.api.config.MaterializationConfiguration;
import ar.com.kfgodel.associative.materialization.api.config.Materializer;
import ar.com.kfgodel.associative.materialization.api.context.MaterializationContext;
import ar.com.kfgodel.associative.materialization.impl.maps.MaterializationMap;
import ar.com.kfgodel.associative.materialization.impl.maps.MaterializationMapImpl;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.optionals.Optional;

/**
 * Created by kfgodel on 17/08/15.
 */
public class MaterializationContextImpl implements MaterializationContext {

    private MaterializationConfiguration configuration;
    private MaterializationMap materializationPerDefinition;

    public static MaterializationContextImpl create(MaterializationConfiguration configuration) {
        MaterializationContextImpl context = new MaterializationContextImpl();
        context.configuration = configuration;
        context.materializationPerDefinition = MaterializationMapImpl.create();
        return context;
    }

    @Override
    public DecomposableTask getBestMaterializationProcessFor(ExpectedObjectDefinition objectDefinition) {
        return getBestMaterializerFor(objectDefinition).describeProcessFor(objectDefinition, this);
    }

    @Override
    public MagiRepo getRepository() {
        return configuration.getMagiRepository();
    }

    @Override
    public MaterializationMap materializations() {
        return materializationPerDefinition;
    }

    private Materializer getBestMaterializerFor(ExpectedObjectDefinition definition) {
        Optional<Materializer> materializer = configuration.materializers().getFirstMatchingFor(definition);
        return materializer.orElseThrow(() -> new InterpretationException("There's no suitable materializer for definition: " + definition));
    }
}
