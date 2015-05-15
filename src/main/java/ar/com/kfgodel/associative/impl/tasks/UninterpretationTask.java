package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.api.unknown.Uninterpretable;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;

/**
 * Created by kfgodel on 14/05/15.
 */
public class UninterpretationTask implements DecomposableTask {

    private InterpretationContext context;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        Identity uninterpretableIdentity = context.getIdentityFor(Uninterpretable.class);
        context.storeInterpretation(uninterpretableIdentity, Uninterpretable.class);
        return uninterpretableIdentity;
    }

    public static UninterpretationTask create(InterpretationContext context) {
        UninterpretationTask task = new UninterpretationTask();
        task.context = context;
        return task;
    }

}
