package ar.com.kfgodel.associative.api.config;

import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * This type represents an interpreter that knows how to create tasks to interpret
 * entities
 *
 * Created by kfgodel on 13/05/15.
 */
public interface Interpreter {

    /**
     * Creates the task that represents the process to interpret the given entity
     * @param entity The entity to interpret
     * @param interpretationContext The context in which to do it
     * @return The task to be executed for its interpretation
     */
    DecomposableTask describeProcessFor(Object entity, InterpretationContext interpretationContext);
}
