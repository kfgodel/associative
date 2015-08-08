package ar.com.kfgodel.associative.identification.impl.maps;

import ar.com.kfgodel.associative.identification.api.ConceptRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.RelationRepresentation;
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
    public Nary<Identity> getRelations() {
        return NaryFromNative.create(interpretationPerIdentity.entrySet().stream()
                .filter((entry) -> RelationRepresentation.class.isInstance(entry.getValue()))
                .map(Map.Entry::getKey));
    }

    @Override
    public Nary<Identity> getConcepts() {
        return NaryFromNative.create(interpretationPerIdentity.entrySet().stream()
                .filter((entry) -> ConceptRepresentation.class.isInstance(entry.getValue()))
                .map(Map.Entry::getKey));
    }

    @Override
    public Nary<Identity> getPercepts() {
        return NaryFromNative.create(interpretationPerIdentity.entrySet().stream()
                .filter((entry) -> !ConceptRepresentation.class.isInstance(entry.getValue()) && !RelationRepresentation.class.isInstance(entry.getValue()))
                .map(Map.Entry::getKey));
    }

    public static RepresentationMapImpl create() {
        RepresentationMapImpl map = new RepresentationMapImpl();
        map.interpretationPerIdentity = new HashMap<>();
        return map;
    }

}
