package ar.com.kfgodel.associative.materialization.impl.tasks;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.materialization.api.context.MaterializationContext;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.optionals.Optional;

import java.util.function.Consumer;

/**
 * Created by kfgodel on 17/08/15.
 */
public class PerceptMaterializationTask implements DecomposableTask {

    private ExpectedObjectDefinition definition;
    private MaterializationContext materializationContext;
    private Consumer<Object> contextualizer;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        MagiRepo repo = materializationContext.getRepository();
        Optional<Object> percept = repo.retrievePerceptById(definition.getIdentificator());
        // Make this percept re usable from context so this task is not needed again
        percept.ifPresent(contextualizer);
        return percept;
    }

    public static PerceptMaterializationTask create(ExpectedObjectDefinition definition, MaterializationContext materializationContext, Consumer<Object> contextualizer) {
        PerceptMaterializationTask task = new PerceptMaterializationTask();
        task.definition = definition;
        task.materializationContext = materializationContext;
        task.contextualizer = contextualizer;
        return task;
    }

}
