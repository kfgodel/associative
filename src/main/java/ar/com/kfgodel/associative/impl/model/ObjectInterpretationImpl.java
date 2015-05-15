package ar.com.kfgodel.associative.impl.model;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectInterpretation;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.List;

/**
 * Created by kfgodel on 14/05/15.
 */
public class ObjectInterpretationImpl implements ObjectInterpretation {

    private List<Identity> relations;

    @Override
    public Nary<Identity> relations() {
        return NaryFromNative.create(relations.stream());
    }

    public static ObjectInterpretationImpl create(List<Identity> partIdentities) {
        ObjectInterpretationImpl interpretation = new ObjectInterpretationImpl();
        interpretation.relations = partIdentities;
        return interpretation;
    }

}
