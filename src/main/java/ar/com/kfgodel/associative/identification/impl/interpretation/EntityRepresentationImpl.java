package ar.com.kfgodel.associative.identification.impl.interpretation;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.ObjectRepresentation;
import ar.com.kfgodel.associative.identification.api.RelationRepresentation;
import ar.com.kfgodel.associative.identification.impl.maps.EntityMap;
import ar.com.kfgodel.associative.identification.impl.maps.RepresentationMap;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;

/**
 * Created by kfgodel on 13/05/15.
 */
public class EntityRepresentationImpl implements EntityRepresentation {

    private EntityMap identities;
    private RepresentationMap representations;

    @Override
    public Nary<Identity> identities() {
        return representations.getIdentities();
    }

    @Override
    public Nary<RelationRepresentation> relations() {
        return representations.getRelations();
    }

    @Override
    public Nary<ObjectRepresentation> concepts() {
        return representations.getConcepts();
    }

    @Override
    public Nary<Object> percepts() {
        return representations.getPercepts();
    }

    @Override
    public Optional<Identity> identityOf(Object reference) {
        return identities.getIdentityFor(reference);
    }

    @Override
    public <R> Optional<R> representationOf(Identity reference) {
        return representations.getInterpretationFor(reference);
    }

    public static EntityRepresentationImpl create(EntityMap identitiesPerEntity, RepresentationMap interpretationPerIdentity) {
        EntityRepresentationImpl interpretation = new EntityRepresentationImpl();
        interpretation.identities = identitiesPerEntity;
        interpretation.representations = interpretationPerIdentity;
        return interpretation;
    }

}
