package ar.com.kfgodel.associative.impl.maps;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectInterpretation;
import ar.com.kfgodel.associative.api.RelationInterpretation;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by kfgodel on 14/05/15.
 */
public class InterpretationMapImpl implements InterpretationMap {

    private Map<Identity, Object> interpretationPerIdentity;

    @Override
    public void put(Identity entityIdentity, Object entityInterpretation) {
        interpretationPerIdentity.put(entityIdentity, entityInterpretation);
    }

    @Override
    public <R> Optional<R> getInterpretationFor(Identity reference) {
        Optional optional = Optional.ofNullable(interpretationPerIdentity.get(reference));
        return optional;
    }

    @Override
    public Nary<Identity> getIdentities() {
        return NaryFromNative.create(interpretationPerIdentity.keySet().stream());
    }

    @Override
    public Nary<RelationInterpretation> getRelations() {
        return NaryFromNative.create(interpretationPerIdentity.values().stream()
                .filter(RelationInterpretation.class::isInstance)
                .map(RelationInterpretation.class::cast));
    }

    @Override
    public Nary<ObjectInterpretation> getObjects() {
        return NaryFromNative.create(interpretationPerIdentity.values().stream()
                .filter(ObjectInterpretation.class::isInstance)
                .map(ObjectInterpretation.class::cast));
    }

    public static InterpretationMapImpl create() {
        InterpretationMapImpl map = new InterpretationMapImpl();
        map.interpretationPerIdentity = new HashMap<>();
        return map;
    }

}
