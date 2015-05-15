package ar.com.kfgodel.associative.impl.model;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.RelationInterpretation;

/**
 * Created by kfgodel on 14/05/15.
 */
public class RelationInterpretationImpl implements RelationInterpretation {

    private Identity origin;
    private Identity relationType;
    private Identity destination;

    @Override
    public Identity origin() {
        return origin;
    }

    @Override
    public Identity relationType() {
        return relationType;
    }

    @Override
    public Identity destination() {
        return destination;
    }

    public static RelationInterpretationImpl create(Identity origin, Identity type, Identity destination) {
        RelationInterpretationImpl interpretation = new RelationInterpretationImpl();
        interpretation.destination = destination;
        interpretation.origin = origin;
        interpretation.relationType = type;
        return interpretation;
    }

}
