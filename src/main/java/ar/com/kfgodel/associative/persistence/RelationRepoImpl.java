package ar.com.kfgodel.associative.persistence;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import ar.com.kfgodel.optionals.Optional;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kfgodel on 04/08/2015.
 */
public class RelationRepoImpl implements RelationRepo {

    private List<PersistentRelation> relations;
    private Map<PersistentRelation, Long> identificatorPerRelation;

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

    @Override
    public Optional<Long> getIdentificatorOf(PersistentRelation relation) {
        Long identificator = identificatorPerRelation.get(relation);
        return NaryFromNative.ofNullable(identificator);
    }

    @Override
    public void persistWith(Long relationIdentificator, PersistentRelation persistentRelation) {
        identificatorPerRelation.put(persistentRelation, relationIdentificator);
        add(persistentRelation);
    }

    public static RelationRepoImpl create() {
        RelationRepoImpl repo = new RelationRepoImpl();
        repo.relations = new LinkedList<>();
        repo.identificatorPerRelation = new LinkedHashMap<>();
        return repo;
    }
}
