package ar.com.kfgodel.associative.impl.maps;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectRepresentation;
import ar.com.kfgodel.associative.api.RelationRepresentation;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;

/**
 * Created by kfgodel on 14/05/15.
 */
public interface RepresentationMap {
    void put(Identity entityIdentity, Object entityInterpretation);

    <R> Optional<R> getInterpretationFor(Identity reference);

    Nary<Identity> getIdentities();

    Nary<RelationRepresentation> getRelations();

    Nary<ObjectRepresentation> getObjects();
}
