package ar.com.kfgodel.associative.persistence;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.optionals.Optional;

/**
 * Created by kfgodel on 04/08/2015.
 */
public interface RelationRepo {

    void add(PersistentRelation relation);

    int relationCount();

    Nary<ConceptResult> findConcept(ConceptPredicate conceptPredicate);

    Optional<Long> getIdentificatorOf(PersistentRelation relation);

    void persistWith(Long relationIdentificator, PersistentRelation persistentRelation);
}
