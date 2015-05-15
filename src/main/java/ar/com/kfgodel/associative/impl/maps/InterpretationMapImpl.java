package ar.com.kfgodel.associative.impl.maps;

import ar.com.kfgodel.associative.api.Identity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfgodel on 14/05/15.
 */
public class InterpretationMapImpl implements InterpretationMap {

    private Map<Identity, Object> interpretationPerIdentity;

    @Override
    public void put(Identity entityIdentity, Object entityInterpretation) {
        interpretationPerIdentity.put(entityIdentity, entityInterpretation);
    }

    public static InterpretationMapImpl create() {
        InterpretationMapImpl map = new InterpretationMapImpl();
        map.interpretationPerIdentity = new HashMap<>();
        return map;
    }

}
