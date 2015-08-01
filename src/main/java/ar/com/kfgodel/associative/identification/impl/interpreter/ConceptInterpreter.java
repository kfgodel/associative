package ar.com.kfgodel.associative.identification.impl.interpreter;

import ar.com.kfgodel.associative.identification.api.config.Interpreter;
import ar.com.kfgodel.associative.identification.api.context.InterpretationContext;
import ar.com.kfgodel.associative.identification.impl.tasks.ConceptInterpretationTask;
import ar.com.kfgodel.associative.identification.impl.tasks.IdentityAssignationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * Created by kfgodel on 14/05/15.
 */
public class ConceptInterpreter implements Interpreter {

    @Override
    public DecomposableTask describeProcessFor(Object entity, InterpretationContext interpretationContext) {
        return IdentityAssignationTask.create(interpretationContext, entity, (newIdentity)-> ConceptInterpretationTask.create(newIdentity, entity, interpretationContext));
    }

    public static ConceptInterpreter create() {
        ConceptInterpreter interpreter = new ConceptInterpreter();
        return interpreter;
    }

}
