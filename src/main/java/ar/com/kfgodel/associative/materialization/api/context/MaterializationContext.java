package ar.com.kfgodel.associative.materialization.api.context;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.materialization.impl.maps.MaterializationMap;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

/**
 * This type represents the working context of a materialization process
 * Created by kfgodel on 17/08/15.
 */
public interface MaterializationContext {
    /**
     * Looks for the best process in the configuration and returns the task that describes it
     * @param objectDefinition The object definition to be materialized
     * @return The task that when executed will produce the object
     */
    DecomposableTask getBestMaterializationProcessFor(ExpectedObjectDefinition objectDefinition);

    /**
     * Gets the memory storage repo
     * @return
     */
    MagiRepo getRepository();

    MaterializationMap materializations();
}
