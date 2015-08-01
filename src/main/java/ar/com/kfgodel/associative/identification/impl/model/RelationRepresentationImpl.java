package ar.com.kfgodel.associative.identification.impl.model;

import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.RelationRepresentation;

/**
 * Created by kfgodel on 14/05/15.
 */
public class RelationRepresentationImpl implements RelationRepresentation {

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

    public static RelationRepresentationImpl create(Identity origin, Identity type, Identity destination) {
        RelationRepresentationImpl interpretation = new RelationRepresentationImpl();
        interpretation.destination = destination;
        interpretation.origin = origin;
        interpretation.relationType = type;
        return interpretation;
    }

}
