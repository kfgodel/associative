package ar.com.kfgodel.associative.persistence;

import ar.com.kfgodel.nary.api.Nary;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kfgodel on 04/08/2015.
 */
public class RelationRepoImpl implements RelationRepo {

    private List<PersistentRelation> relations;

    @Override
    public void add(PersistentRelation relation) {
        relations.add(relation);
    }

    @Override
    public int relationCount() {
        return relations.size();
    }

    @Override
    public Nary<ConceptResult> findConcept(ConceptPredicate conceptPredicate) {
        MatchedConcept matchedConcept = MatchedConcept.create(conceptPredicate);
        for (PersistentRelation relation : relations) {
            matchedConcept.consider(relation);
        }
        return matchedConcept.calculateResults();
    }

    public static RelationRepoImpl create() {
        RelationRepoImpl repo = new RelationRepoImpl();
        repo.relations = new LinkedList<>();
        return repo;
    }
}
