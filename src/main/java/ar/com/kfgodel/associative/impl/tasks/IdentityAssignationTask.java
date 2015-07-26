package ar.com.kfgodel.associative.impl.tasks;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;

import java.util.Optional;
import java.util.function.Function;

/**
 * This type represents the task of assigning an identity for an entity in the interpretation context.<br>
 * If the entity already has an identity in the context, this task prevents an infinite loop for self referencing entities
 * by using the existing identity, and avoinding any further identification action
 * Created by kfgodel on 26/07/15.
 */
public class IdentityAssignationTask implements DecomposableTask {

    private InterpretationContext context;
    private Object entity;
    private Function<Identity, Object> interpretationAction;


    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        Optional<Identity> existingIdentity = context.getIdentityFor(entity);
        if(existingIdentity.isPresent()){
            // There's another ongoing task who has ownership of the entity
            return existingIdentity.get();
        }
        Identity newIdentity = context.createIdentityFor(entity);
        return interpretationAction.apply(newIdentity);
    }

    public static IdentityAssignationTask create(InterpretationContext context, Object entity, Function<Identity, Object> interpretationAction) {
        IdentityAssignationTask task = new IdentityAssignationTask();
        task.context = context;
        task.entity = entity;
        task.interpretationAction = interpretationAction;
        return task;
    }


}
