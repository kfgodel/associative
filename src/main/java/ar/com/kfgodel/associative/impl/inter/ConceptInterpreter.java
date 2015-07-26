package ar.com.kfgodel.associative.impl.inter;

import ar.com.kfgodel.associative.api.config.Interpreter;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.impl.tasks.ConceptInterpretationTask;
import ar.com.kfgodel.associative.impl.tasks.IdentityAssignationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * Created by kfgodel on 14/05/15.
 */
public class ConceptInterpreter implements Interpreter {

    @Override
    public DecomposableTask describeProcessFor(Object entity, InterpretationContext interpretationContext) {
        return IdentityAssignationTask.create(interpretationContext, entity, ConceptInterpretationTask.create(entity, interpretationContext));
    }

    public static ConceptInterpreter create() {
        ConceptInterpreter interpreter = new ConceptInterpreter();
        return interpreter;
    }

}
