package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;

/**
 * Created by kfgodel on 14/05/15.
 */
public abstract class IdentificationTaskSupport implements DecomposableTask {

    private InterpretationContext context;
    private Object entity;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        if(context.hasIdentityFor(entity)){
            // There's another ongoing task who has ownership
            return context.getOrCreateIdentityFor(entity);
        }
        Identity newIdentity = context.getOrCreateIdentityFor(entity);
        return continueIdentification(newIdentity);
    }

    protected abstract Object continueIdentification(Identity newIdentity);

    protected InterpretationContext getContext() {
        return context;
    }

    protected void setContext(InterpretationContext context) {
        this.context = context;
    }

    protected Object getEntity() {
        return entity;
    }

    protected void setEntity(Object entity) {
        this.entity = entity;
    }
}
