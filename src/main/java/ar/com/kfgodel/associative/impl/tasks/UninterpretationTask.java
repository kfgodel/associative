package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.api.unknown.Uninterpretable;
import ar.com.kfgodel.associative.impl.tasks.helper.RecursionAvoider;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;

/**
 * Created by kfgodel on 14/05/15.
 */
public class UninterpretationTask implements DecomposableTask{

    private Object entity;
    private InterpretationContext context;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        return RecursionAvoider.create(entity, context)
                .executeIfNoPreviousIdentity((newIdentity)->{
                    context.storeInterpretation(newIdentity, Uninterpretable.class);
                    return newIdentity;
                });
    }

    public static UninterpretationTask create(Object entity, InterpretationContext context) {
        UninterpretationTask task = new UninterpretationTask();
        task.context = context;
        task.entity = entity;
        return task;
    }

}
