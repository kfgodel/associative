package ar.com.kfgodel.associative.impl.interpreter;

import ar.com.kfgodel.associative.api.config.Interpreter;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.impl.tasks.IdentityAssignationTask;
import ar.com.kfgodel.associative.impl.tasks.PerceptInterpretationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * Created by kfgodel on 26/07/15.
 */
public class PerceptInterpreter implements Interpreter {
    @Override
    public DecomposableTask describeProcessFor(Object entity, InterpretationContext interpretationContext) {
        return IdentityAssignationTask.create(interpretationContext, entity, (newIdentity) -> PerceptInterpretationTask.create(newIdentity, entity, interpretationContext));
    }

    public static PerceptInterpreter create() {
        PerceptInterpreter interpreter = new PerceptInterpreter();
        return interpreter;
    }

}
