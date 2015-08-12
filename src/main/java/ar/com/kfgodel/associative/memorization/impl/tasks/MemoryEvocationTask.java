package ar.com.kfgodel.associative.memorization.impl.tasks;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.RelationRepresentation;
import ar.com.kfgodel.associative.memorization.impl.memories.InterpretationMemoryImpl;
import ar.com.kfgodel.associative.persistence.ConceptPredicate;
import ar.com.kfgodel.associative.persistence.ConceptResult;
import ar.com.kfgodel.associative.persistence.RelationPredicate;
import ar.com.kfgodel.associative.persistence.RestrictedSourceAndType;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.optionals.Optional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kfgodel on 12/08/2015.
 */
public class MemoryEvocationTask implements DecomposableTask {

    private EntityRepresentation representation;
    private Long identificator;
    private MagiRepo repo;
    private InterpretationMemoryImpl evokedMemory;
    private Object entity;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        evokedMemory = InterpretationMemoryImpl.create(representation);
        final Optional<Identity> entityIdentity = representation.identityOf(entity);
        evokedMemory.assignTo(entityIdentity.get(), identificator);

        evokePercepts();
        evokeConcept();
        return null;
    }

    private void evokeConcept() {
        ConceptPredicate conceptPredicate = createPredicateFor(representation);
        Nary<ConceptResult> conceptResult = repo.retrieveConcept(conceptPredicate);
        final List<ConceptResult> concepts = conceptResult.collect(Collectors.toList());
        System.out.println(concepts);
    }

    private void evokePercepts() {
        for (Identity perceptIdentity : representation.percepts()) {
            Optional<Object> percept = representation.representationOf(perceptIdentity);
            Optional<Long> perceptIdentificator = repo.retrievePercept(percept.get());
            evokedMemory.assignTo(perceptIdentity, perceptIdentificator.get());
        }
    }

    private ConceptPredicate createPredicateFor(EntityRepresentation representation) {
        ConceptPredicate conceptPredicate = ConceptPredicate.create();
        for (Identity conceptRelationIdentity : representation.relations()) {
            Optional<RelationRepresentation> relationRepresentation = representation.representationOf(conceptRelationIdentity);
            Long restrictedSource = evokedMemory.identificatorOf(relationRepresentation.get().origin());
            Long restrictedType = evokedMemory.identificatorOf(relationRepresentation.get().relationType());
            RelationPredicate relationPredicate = RestrictedSourceAndType.create(restrictedSource, restrictedType);
            conceptPredicate.addPredicate(relationPredicate);
        }
        return conceptPredicate;
    }

    public static MemoryEvocationTask create(Object summoned, EntityRepresentation representation, Long identificator, MagiRepo repo) {
        MemoryEvocationTask task = new MemoryEvocationTask();
        task.representation = representation;
        task.identificator = identificator;
        task.entity= summoned;
        task.repo = repo;
        return task;
    }
}
