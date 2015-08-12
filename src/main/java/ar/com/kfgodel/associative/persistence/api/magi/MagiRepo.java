package ar.com.kfgodel.associative.persistence.api.magi;

import ar.com.kfgodel.associative.persistence.ConceptPredicate;
import ar.com.kfgodel.associative.persistence.ConceptResult;
import ar.com.kfgodel.associative.persistence.PersistentRelation;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.optionals.Optional;

/**
 * This type represents the single view of 3 different stores for data persistence
 * Created by kfgodel on 07/08/15.
 */
public interface MagiRepo {
    Long storePercept(Object percept);

    Long storeConceptIdentity(ConceptPredicate conceptPredicate);

    Long storeRelation(PersistentRelation persistentRelation);

    Optional<Long> retrievePercept(Object percept);

    Nary<ConceptResult> retrieveConcept(ConceptPredicate conceptPredicate);
}
