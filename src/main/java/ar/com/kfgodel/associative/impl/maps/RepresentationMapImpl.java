package ar.com.kfgodel.associative.impl.maps;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectRepresentation;
import ar.com.kfgodel.associative.api.RelationRepresentation;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by kfgodel on 14/05/15.
 */
public class RepresentationMapImpl implements RepresentationMap {

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
    public Nary<RelationRepresentation> getRelations() {
        return NaryFromNative.create(interpretationPerIdentity.values().stream()
                .filter(RelationRepresentation.class::isInstance)
                .map(RelationRepresentation.class::cast));
    }

    @Override
    public Nary<ObjectRepresentation> getObjects() {
        return NaryFromNative.create(interpretationPerIdentity.values().stream()
                .filter(ObjectRepresentation.class::isInstance)
                .map(ObjectRepresentation.class::cast));
    }

    public static RepresentationMapImpl create() {
        RepresentationMapImpl map = new RepresentationMapImpl();
        map.interpretationPerIdentity = new HashMap<>();
        return map;
    }

}
