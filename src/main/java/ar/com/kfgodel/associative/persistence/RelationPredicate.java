package ar.com.kfgodel.associative.persistence;

/**
 * Created by kfgodel on 12/08/2015.
 */
public interface RelationPredicate {

    boolean matches(PersistentRelation relation);
}
