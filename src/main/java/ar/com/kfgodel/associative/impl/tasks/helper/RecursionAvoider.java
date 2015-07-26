package ar.com.kfgodel.associative.impl.tasks.helper;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.context.InterpretationContext;

import java.util.Optional;
import java.util.function.Function;

/**
 * This class implements a recursion cut that verifies identity in context before continuing execution.<br>
 *     If the entity has an indentity in context it uses that, if not it continues the interpretation process.<br>
 *     This prevents the infinite loop of an entity with references to itself
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
        Optional<Identity> existingIdentity = context.getIdentityFor(entity);
        if(existingIdentity.isPresent()){
            // There's another ongoing task who has ownership of the entity
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
