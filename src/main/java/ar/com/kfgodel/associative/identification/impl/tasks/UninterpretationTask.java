package ar.com.kfgodel.associative.identification.impl.tasks;

import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.context.InterpretationContext;
import ar.com.kfgodel.associative.identification.impl.model.UninterpretableRepresentationImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;

/**
 * This type represents the action of not interpreting an entity.<br>
 *     The entity is represented by the Uninterpretable object
 * Created by kfgodel on 14/05/15.
 */
public class UninterpretationTask implements DecomposableTask{

    private Identity identity;
    private InterpretationContext context;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        context.storeRepresentationFor(identity, UninterpretableRepresentationImpl.INSTANCE);
        return identity;
    }

    public static UninterpretationTask create(Identity identity, InterpretationContext context) {
        UninterpretationTask task = new UninterpretationTask();
        task.identity = identity;
        task.context = context;
        return task;
    }

}
