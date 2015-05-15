package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.api.unknown.Uninterpretable;

/**
 * Created by kfgodel on 14/05/15.
 */
public class UninterpretationTask extends IdentificationTaskSupport{

    @Override
    protected Object continueIdentification(Identity newIdentity) {
        getContext().storeInterpretation(newIdentity, Uninterpretable.class);
        return newIdentity;
    }

    public static UninterpretationTask create(Object entity, InterpretationContext context) {
        UninterpretationTask task = new UninterpretationTask();
        task.setContext(context);
        task.setEntity(entity);
        return task;
    }

}
