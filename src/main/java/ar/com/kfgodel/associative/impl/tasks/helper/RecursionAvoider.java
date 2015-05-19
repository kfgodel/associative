package ar.com.kfgodel.associative.impl.tasks.helper;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.context.InterpretationContext;

import java.util.function.Function;

/**
 * This class implements a recursion cut, that verifies identity in context before continuing execution
 * Created by kfgodel on 19/05/2015.
 */
public class RecursionAvoider {
    private InterpretationContext context;
    private Object entity;


    /**
     * Exxecutes the given action, only if the entity is not already assigned an identity in the current context
     * @param action The action to execute to get the result
     * @return The previous identity, or the result of the action
     */
    public Object executeIfNoPreviousIdentity(Function<Identity, Object> action) {
        if(context.hasIdentityFor(entity)){
            // There's another ongoing task who has ownership
            return context.getOrCreateIdentityFor(entity);
        }
        Identity newIdentity = context.getOrCreateIdentityFor(entity);
        return action.apply(newIdentity);
    }

    public static RecursionAvoider create(Object entity, InterpretationContext context) {
        RecursionAvoider cutter = new RecursionAvoider();
        cutter.context = context;
        cutter.entity = entity;
        return cutter;
    }
}
