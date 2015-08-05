package ar.com.kfgodel.associative.persistence;

import java.util.List;

/**
 * Created by kfgodel on 05/08/2015.
 */
public class ConceptResultImpl implements ConceptResult {

    private List<PersistentRelation> relations;
    private ConceptPredicate conceptPredicate;
    private Long source;

    public static ConceptResultImpl create(Long commonSource, List<PersistentRelation> combinationOption, ConceptPredicate conceptPredicate) {
        ConceptResultImpl result = new ConceptResultImpl();
        result.relations = combinationOption;
        result.conceptPredicate = conceptPredicate;
        result.source = commonSource;
        return result;
    }

    @Override
    public List<PersistentRelation> getRelations() {
        return relations;
    }

    @Override
    public Long getSource() {
        return source;
    }
}
