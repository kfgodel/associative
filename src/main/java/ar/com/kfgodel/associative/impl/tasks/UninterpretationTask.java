package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.api.unknown.Uninterpretable;

import java.util.function.Function;

/**
 * This type represents the action of not interpreting an entity.<br>
 *     The entity is represented by the Uninterpretable object
 * Created by kfgodel on 14/05/15.
 */
public class UninterpretationTask implements Function<Identity, Object>{

    private InterpretationContext context;

    @Override
    public Object apply(Identity identity) {
        context.storeInterpretation(identity, Uninterpretable.class);
        return identity;
    }

    public static UninterpretationTask create(InterpretationContext context) {
        UninterpretationTask task = new UninterpretationTask();
        task.context = context;
        return task;
    }

}
