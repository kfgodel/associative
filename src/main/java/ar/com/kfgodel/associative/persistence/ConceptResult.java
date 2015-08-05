package ar.com.kfgodel.associative.persistence;

import java.util.List;

/**
 * Created by kfgodel on 04/08/2015.
 */
public interface ConceptResult {
    List<PersistentRelation> getRelations();

    Long getSource();
}
