package ar.com.kfgodel.associative.impl.inter;

import ar.com.kfgodel.associative.api.EntityInterpretation;
import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectInterpretation;
import ar.com.kfgodel.associative.api.RelationInterpretation;
import ar.com.kfgodel.associative.impl.maps.EntityMap;
import ar.com.kfgodel.associative.impl.maps.InterpretationMap;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;

/**
 * Created by kfgodel on 13/05/15.
 */
public class EntityInterpretationImpl implements EntityInterpretation {

    private EntityMap identities;
    private InterpretationMap representations;

    @Override
    public Nary<Identity> identities() {
        return representations.getIdentities();
    }

    @Override
    public Nary<RelationInterpretation> relations() {
        return representations.getRelations();
    }

    @Override
    public Nary<ObjectInterpretation> objects() {
        return representations.getObjects();
    }

    @Override
    public Optional<Identity> identityOf(Object reference) {
        return identities.getIdentityFor(reference);
    }

    @Override
    public <R> Optional<R> representationOf(Identity reference) {
        return representations.getInterpretationFor(reference);
    }

    public static EntityInterpretationImpl create(EntityMap identitiesPerEntity, InterpretationMap interpretationPerIdentity) {
        EntityInterpretationImpl interpretation = new EntityInterpretationImpl();
        interpretation.identities = identitiesPerEntity;
        interpretation.representations = interpretationPerIdentity;
        return interpretation;
    }

}
