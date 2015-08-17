package ar.com.kfgodel.associative.materialization.impl.tasks;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.materialization.api.context.MaterializationContext;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.decomposer.api.results.DelayResult;
import ar.com.kfgodel.optionals.Optional;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This tasl tries to reutilize the existing materialization in context for an expected object, so
 * no infinite loop is generated when an object depends on itself
 * Created by kfgodel on 17/08/15.
 */
public class MaterializationFromContextTask implements DecomposableTask {

    private ExpectedObjectDefinition definition;
    private MaterializationContext materializationContext;
    private Function<Consumer<Object>, DecomposableTask> nextStepGenerator;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        Optional<Object> existingMaterialization = materializationContext.materializations().getFor(definition);
        if(existingMaterialization.isPresent()){
            // There's another task who has taken ownership of the materialization. Lets's use its own
            return existingMaterialization.get();
        }
        DecomposableTask materializationStep = nextStepGenerator.apply(this::storeMaterializationInContext);
        return DelayResult.waitingFor(materializationStep);
    }

    private void storeMaterializationInContext(Object materialization) {
        materializationContext.materializations().storeFor(definition, materialization);
    }

    public static MaterializationFromContextTask create(ExpectedObjectDefinition definition, MaterializationContext materializationContext, Function<Consumer<Object>, DecomposableTask> nextStepGenerator) {
        MaterializationFromContextTask task = new MaterializationFromContextTask();
        task.definition = definition;
        task.materializationContext = materializationContext;
        task.nextStepGenerator = nextStepGenerator;
        return task;
    }

}
