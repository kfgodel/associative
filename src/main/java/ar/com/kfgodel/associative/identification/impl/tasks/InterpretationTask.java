package ar.com.kfgodel.associative.identification.impl.tasks;

import ar.com.kfgodel.associative.identification.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.identification.api.context.InterpretationContext;
import ar.com.kfgodel.associative.identification.impl.context.InterpretationContextImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.decomposer.api.results.DelayResult;
import ar.com.kfgodel.tostring.ImplementedWithStringer;
import ar.com.kfgodel.tostring.Stringer;

/**
 * This type represents the task of interpreting an external entity inside the associative memory.
 * This is the main entry point to the association process generating and interpretation context
 * that is used on subtasks. The result of this task is an EntityInterpretation
 *
 * Created by kfgodel on 13/05/15.
 */
public class InterpretationTask implements DecomposableTask {

    private Object entity;
    private InterpretationConfiguration config;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        InterpretationContext context = InterpretationContextImpl.create(config);

        DecomposableTask interpretationProcess = context.getBestInterpretationProcessFor(entity);
        return DelayResult.waitingFor(interpretationProcess)
                .andFinally(endContext -> context.completeRepresentation());
    }

    public static InterpretationTask create(Object entity, InterpretationConfiguration configuration) {
        InterpretationTask task = new InterpretationTask();
        task.entity = entity;
        task.config = configuration;
        return task;
    }

    @Override
    @ImplementedWithStringer
    public String toString() {
        return Stringer.representationOf(this);
    }
}
