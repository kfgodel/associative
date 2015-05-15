package ar.com.kfgodel.associative.impl.inter;

import ar.com.kfgodel.associative.api.config.Interpreter;
import ar.com.kfgodel.associative.impl.context.InterpretationContextImpl;
import ar.com.kfgodel.associative.impl.tasks.UninterpretationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * Created by kfgodel on 14/05/15.
 */
public class UninterpretableIntepreter implements Interpreter {

    @Override
    public DecomposableTask describeProcessFor(Object entity, InterpretationContextImpl interpretationContext) {
        return UninterpretationTask.create(entity, interpretationContext);
    }

    public static UninterpretableIntepreter create() {
        UninterpretableIntepreter intepreter = new UninterpretableIntepreter();
        return intepreter;
    }

}
