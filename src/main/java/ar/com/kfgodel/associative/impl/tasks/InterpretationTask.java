package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.impl.context.InterpretationContextImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.decomposer.api.results.DelayResult;

/**
 * This type represents the task of interpreting an external entity inside the associative memory
 * to get an entity interpretation
 *
 * Created by kfgodel on 13/05/15.
 */
public class InterpretationTask implements DecomposableTask {

    private Object entity;
    private InterpretationConfiguration config;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        InterpretationContext context = InterpretationContextImpl.create(config);
        DecomposableTask interpretationProcess = context.getBestProcessFor(entity);
        return DelayResult.waitingFor(interpretationProcess)
                .andFinally(endContext -> context.getInterpretation());
    }

    public static InterpretationTask create(Object entity, InterpretationConfiguration configuration) {
        InterpretationTask task = new InterpretationTask();
        task.entity = entity;
        task.config = configuration;
        return task;
    }

}