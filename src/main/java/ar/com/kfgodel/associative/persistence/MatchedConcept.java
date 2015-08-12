package ar.com.kfgodel.associative.persistence;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by kfgodel on 05/08/2015.
 */
public class MatchedConcept {

    private ConceptPredicate conceptPredicate;
    private Map<RelationPredicate, LinkedHashSet<PersistentRelation>> matchedRelations;

    public static MatchedConcept create(ConceptPredicate conceptPredicate) {
        MatchedConcept concept = new MatchedConcept();
        concept.conceptPredicate = conceptPredicate;
        concept.matchedRelations = new HashMap<>();
        concept.initialize();
        return concept;
    }

    private void initialize() {
        final List<RelationPredicate> predicates = conceptPredicate.getRelationPredicates();
        for (RelationPredicate predicate : predicates) {
            matchedRelations.put(predicate, new LinkedHashSet<>());
        }
    }

    public void addMatch(RelationPredicate predicate, PersistentRelation relation) {
        matchedRelations.get(predicate).add(relation);
    }

    public Nary<ConceptResult> calculateResults() {
        final List<LinkedHashSet<PersistentRelation>> matchingGroups = new ArrayList<>(matchedRelations.values());
        final Set<List<PersistentRelation>> relationOptions = Sets.cartesianProduct(matchingGroups);
        List<ConceptResult> foundResults = new ArrayList<>();
        for (List<PersistentRelation> combinationOption : relationOptions) {
            if(containsDuplicate(combinationOption)){
                // A relation may match more than one predicate, but different relations are expected for a result
                continue;
            }
            final Optional<Long> commonSource = extractCommonSourceFrom(combinationOption);
            if(!commonSource.isPresent()){
                // Discard relations that don't belong to same source
                continue;
            }
            ConceptResult result = ConceptResultImpl.create(commonSource.get(), combinationOption, conceptPredicate);
            foundResults.add(result);
        }
        return NaryFromNative.create(foundResults.stream());
    }

    private boolean containsDuplicate(List<PersistentRelation> combinationOption) {
        // Compare each element
        for (int i = 0; i < combinationOption.size() - 1; i++) {
            // with their following elements
            for (int j = i + 1; j < combinationOption.size(); j++) {
                if(combinationOption.get(i) == combinationOption.get(j)){
                    return true;
                }
            }
        }
        return false;
    }

    public Optional<Long> extractCommonSourceFrom(List<PersistentRelation> relations) {
        Long commonSource = null;
        for (PersistentRelation relation : relations) {
            final Long relationSource = relation.getSource();
            if(commonSource == null){
                // We keep the first one to compare with others
                commonSource = relationSource;
            }else if(!commonSource.equals(relationSource)){
                //We reject options that have relations from different sources
                return Optional.empty();
            }
        }
        return Optional.of(commonSource);
    }

    public void consider(PersistentRelation relation) {
        final List<RelationPredicate> predicates = conceptPredicate.getRelationPredicates();
        for (RelationPredicate predicate : predicates) {
            if(predicate.matches(relation)){
                this.addMatch(predicate, relation);
            }
        }
    }
}
