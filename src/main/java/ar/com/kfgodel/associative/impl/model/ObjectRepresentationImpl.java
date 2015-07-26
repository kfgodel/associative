package ar.com.kfgodel.associative.impl.model;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectRepresentation;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.List;

/**
 * Created by kfgodel on 14/05/15.
 */
public class ObjectRepresentationImpl implements ObjectRepresentation {

    private List<Identity> relations;

    @Override
    public Nary<Identity> relations() {
        return NaryFromNative.create(relations.stream());
    }

    public static ObjectRepresentationImpl create(List<Identity> partIdentities) {
        ObjectRepresentationImpl interpretation = new ObjectRepresentationImpl();
        interpretation.relations = partIdentities;
        return interpretation;
    }

}
