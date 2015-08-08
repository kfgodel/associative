package ar.com.kfgodel.associative.persistence.api.magi;

import ar.com.kfgodel.associative.persistence.ConceptPredicate;

/**
 * This type represents the single view of 3 different stores for data persistence
 * Created by kfgodel on 07/08/15.
 */
public interface MagiRepo {
    Long storePercept(Object percept);

    Long storeConceptIdentity(ConceptPredicate conceptPredicate);

    Long storeRelation(Long source, Long type, Long destination);
}
