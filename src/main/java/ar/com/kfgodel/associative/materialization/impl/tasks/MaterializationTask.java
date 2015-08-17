package ar.com.kfgodel.associative.materialization.impl.tasks;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.materialization.api.config.MaterializationConfiguration;
import ar.com.kfgodel.associative.materialization.api.context.MaterializationContext;
import ar.com.kfgodel.associative.materialization.impl.context.MaterializationContextImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.decomposer.api.results.DelayResult;
import ar.com.kfgodel.tostring.ImplementedWithStringer;
import ar.com.kfgodel.tostring.Stringer;

/**
 * This type represents the task of creating an entity from its memory representation
 * Created by kfgodel on 17/08/15.
 */
public class MaterializationTask implements DecomposableTask {

    private ExpectedObjectDefinition objectDefinition;
    private MaterializationConfiguration configuration;


    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        MaterializationContext context = MaterializationContextImpl.create(configuration);

        DecomposableTask materializationProcess = context.getBestMaterializationProcessFor(objectDefinition);
        return DelayResult.waitingFor(materializationProcess);
    }

    public static MaterializationTask create(ExpectedObjectDefinition definition, MaterializationConfiguration config) {
        MaterializationTask task = new MaterializationTask();
        task.configuration = config;
        task.objectDefinition = definition;
        return task;
    }

    @Override
    @ImplementedWithStringer
    public String toString() {
        return Stringer.representationOf(this);
    }


}
