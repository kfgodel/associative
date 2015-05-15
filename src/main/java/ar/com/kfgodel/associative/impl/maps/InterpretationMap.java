package ar.com.kfgodel.associative.impl.maps;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectInterpretation;
import ar.com.kfgodel.associative.api.RelationInterpretation;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;

/**
 * Created by kfgodel on 14/05/15.
 */
public interface InterpretationMap {
    void put(Identity entityIdentity, Object entityInterpretation);

    <R> Optional<R> getInterpretationFor(Identity reference);

    Nary<Identity> getIdentities();

    Nary<RelationInterpretation> getRelations();

    Nary<ObjectInterpretation> getObjects();
}
