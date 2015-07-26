package ar.com.kfgodel.associative.impl.inter;

import ar.com.kfgodel.associative.api.config.Interpreter;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.impl.tasks.IdentityAssignationTask;
import ar.com.kfgodel.associative.impl.tasks.UninterpretationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * Created by kfgodel on 14/05/15.
 */
public class UninterpretableIntepreter implements Interpreter {

    @Override
    public DecomposableTask describeProcessFor(Object entity, InterpretationContext interpretationContext) {
        return IdentityAssignationTask.create(interpretationContext, entity, (newIdentity) -> UninterpretationTask.create(newIdentity, interpretationContext));
    }

    public static UninterpretableIntepreter create() {
        UninterpretableIntepreter intepreter = new UninterpretableIntepreter();
        return intepreter;
    }

}
