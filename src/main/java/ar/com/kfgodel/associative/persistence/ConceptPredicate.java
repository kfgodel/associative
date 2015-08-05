package ar.com.kfgodel.associative.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kfgodel on 04/08/2015.
 */
public class ConceptPredicate {

    private List<RelationPredicate> relationPredicates;

    public List<RelationPredicate> getRelationPredicates() {
        return relationPredicates;
    }

    public static ConceptPredicate create(RelationPredicate... predicates) {
        ConceptPredicate predicate = new ConceptPredicate();
        predicate.relationPredicates = new ArrayList<>();
        for (RelationPredicate relationPredicate : predicates) {
            predicate.relationPredicates.add(relationPredicate);
        }
        return predicate;
    }

    public boolean accepts(ConceptResult result) {
        List<PersistentRelation> relations = result.getRelations();
        Long commonSource = null;
        for (PersistentRelation relation : relations) {
            final Long relationSource = relation.getSource();
            if(commonSource == null){
                // We keep the first one to compare with others
                commonSource = relationSource;
            }else if(!commonSource.equals(relationSource)){
                //We reject options that have relations from different sources
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +  this.relationPredicates;
    }
}
