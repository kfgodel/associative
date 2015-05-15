package ar.com.kfgodel.associative.impl.inter;

import ar.com.kfgodel.associative.api.EntityInterpretation;
import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectInterpretation;
import ar.com.kfgodel.associative.api.RelationInterpretation;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;

/**
 * Created by kfgodel on 13/05/15.
 */
public class EntityInterpretationImpl implements EntityInterpretation {
    @Override
    public Nary<Identity> entities() {
        return null;
    }

    @Override
    public Nary<RelationInterpretation> relations() {
        return null;
    }

    @Override
    public Nary<ObjectInterpretation> objects() {
        return null;
    }

    @Override
    public Optional<Identity> identityOf(Object reference) {
        return null;
    }

    @Override
    public <R> Optional<R> representationOf(Identity reference) {
        return null;
    }

    public static EntityInterpretationImpl create() {
        EntityInterpretationImpl interpretation = new EntityInterpretationImpl();
        return interpretation;
    }

}
