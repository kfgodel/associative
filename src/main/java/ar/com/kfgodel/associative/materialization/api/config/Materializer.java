package ar.com.kfgodel.associative.materialization.api.config;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.materialization.api.context.MaterializationContext;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * This type represents a materializer that knows how to re-create entities and describes the process to do so, with a task
 * Created by kfgodel on 17/08/15.
 */
public interface Materializer {

    /**
     * Creates the task that represents the process to materialize the expected object
     * @param definition The definition of teh expected object
     * @param materializationContext The context in which to do it
     * @return The task to be executed for the materialization to happen
     */
    DecomposableTask describeProcessFor(ExpectedObjectDefinition definition, MaterializationContext materializationContext);

}
