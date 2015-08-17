package ar.com.kfgodel.associative.materialization.impl.maps;

import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import ar.com.kfgodel.optionals.Optional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kfgodel on 17/08/15.
 */
public class MaterializationMapImpl implements MaterializationMap {

    private Map<ExpectedObjectDefinition, Object> materializationPerDefinition;

    public static MaterializationMapImpl create() {
        MaterializationMapImpl map = new MaterializationMapImpl();
        map.materializationPerDefinition = new LinkedHashMap<>();
        return map;
    }

    @Override
    public Optional<Object> getFor(ExpectedObjectDefinition definition) {
        Object materialization = materializationPerDefinition.get(definition);
        return NaryFromNative.ofNullable(materialization);
    }

    @Override
    public void storeFor(ExpectedObjectDefinition definition, Object materialization) {
        materializationPerDefinition.put(definition, materialization);
    }
}
