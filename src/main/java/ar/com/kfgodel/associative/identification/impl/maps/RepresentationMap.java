package ar.com.kfgodel.associative.identification.impl.maps;

import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;

/**
 * Created by kfgodel on 14/05/15.
 */
public interface RepresentationMap {
    void put(Identity entityIdentity, Object entityInterpretation);

    <R> Optional<R> getInterpretationFor(Identity reference);

    Nary<Identity> getIdentities();

    Nary<Identity> getRelations();

    Nary<Identity> getConcepts();

    Nary<Identity> getPercepts();
}
