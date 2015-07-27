package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;

/**
 * Created by kfgodel on 26/07/15.
 */
public class PerceptInterpretationTask implements DecomposableTask {

    private Identity identity;
    private Object entity;
    private InterpretationContext context;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        // The entity it's its own representation because it's a percept
        context.storeRepresentationFor(identity, entity);
        return identity;
    }

    public static PerceptInterpretationTask create(Identity identity, Object entity, InterpretationContext context) {
        PerceptInterpretationTask task = new PerceptInterpretationTask();
        task.identity = identity;
        task.entity = entity;
        task.context = context;
        return task;
    }

}
