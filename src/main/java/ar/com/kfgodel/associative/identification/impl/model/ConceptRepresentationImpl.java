package ar.com.kfgodel.associative.identification.impl.model;

import ar.com.kfgodel.associative.identification.api.ConceptRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.List;

/**
 * Created by kfgodel on 14/05/15.
 */
public class ConceptRepresentationImpl implements ConceptRepresentation {

    private List<Identity> relations;

    @Override
    public Nary<Identity> relations() {
        return NaryFromNative.create(relations.stream());
    }

    public static ConceptRepresentationImpl create(List<Identity> partIdentities) {
        ConceptRepresentationImpl interpretation = new ConceptRepresentationImpl();
        interpretation.relations = partIdentities;
        return interpretation;
    }

}
