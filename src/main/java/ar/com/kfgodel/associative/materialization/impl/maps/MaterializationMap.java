package ar.com.kfgodel.associative.materialization.impl.maps;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.optionals.Optional;

/**
 * Created by kfgodel on 17/08/15.
 */
public interface MaterializationMap {
    Optional<Object> getFor(ExpectedObjectDefinition definition);

    void storeFor(ExpectedObjectDefinition definition, Object materialization);
}
