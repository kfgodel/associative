package ar.com.kfgodel.associative.memorization.impl.tasks;

import ar.com.kfgodel.associative.identification.api.ConceptRepresentation;
import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.RelationRepresentation;
import ar.com.kfgodel.associative.memorization.impl.memories.InterpretationMemoryImpl;
import ar.com.kfgodel.associative.persistence.ConceptPredicate;
import ar.com.kfgodel.associative.persistence.PersistentRelation;
import ar.com.kfgodel.associative.persistence.RelationPredicate;
import ar.com.kfgodel.associative.persistence.RestrictedTypeAndDestination;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.optionals.Optional;

/**
 * Created by kfgodel on 01/08/15.
 */
public class MemorizationTask implements DecomposableTask {

    private EntityRepresentation interpretation;
    private MagiRepo repo;
    private InterpretationMemoryImpl memory;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        memory = InterpretationMemoryImpl.create(interpretation);
        storePercepts();
        storeConcepts();
        storeRelations();
        return memory;
    }

    private void storeConcepts() {
        for (Identity conceptIdentity : interpretation.concepts()) {
            Optional<ConceptRepresentation> conceptRepresentation = interpretation.representationOf(conceptIdentity);
            ConceptPredicate conceptPredicate = createPredicateFor(conceptRepresentation.get());
            Long conceptIdentificator = repo.storeConceptIdentity(conceptPredicate);
            memory.assignTo(conceptIdentity, conceptIdentificator);
        }
    }

    private ConceptPredicate createPredicateFor(ConceptRepresentation concept) {
        ConceptPredicate conceptPredicate = ConceptPredicate.create();
        for (Identity conceptRelationIdentity : concept.relations()) {
            Optional<RelationRepresentation> relationRepresentation = interpretation.representationOf(conceptRelationIdentity);
            Long restrictedType = memory.identificatorOf(relationRepresentation.get().relationType());
            Long restrictedDestination = memory.identificatorOf(relationRepresentation.get().destination());
            RelationPredicate relationPredicate = RestrictedTypeAndDestination.create(restrictedType, restrictedDestination);
            conceptPredicate.addPredicate(relationPredicate);
        }
        return conceptPredicate;
    }

    private void storeRelations() {
        for (Identity relationIdentity : interpretation.relations()) {
            Optional<RelationRepresentation> relationRepresentation = interpretation.representationOf(relationIdentity);
            Long source = memory.identificatorOf(relationRepresentation.get().origin());
            Long type = memory.identificatorOf(relationRepresentation.get().relationType());
            Long destination = memory.identificatorOf(relationRepresentation.get().destination());
            Long relationIdentificator = repo.storeRelation(PersistentRelation.create(source, type, destination));
            memory.assignTo(relationIdentity, relationIdentificator);
        }
    }

    private void storePercepts() {
        for (Identity perceptIdentity : interpretation.percepts()) {
            Optional<Object> percept = interpretation.representationOf(perceptIdentity);
            Long perceptIdentificator = repo.storePercept(percept.get());
            memory.assignTo(perceptIdentity, perceptIdentificator);
        }
    }

    public static MemorizationTask create(EntityRepresentation interpretation, MagiRepo repo) {
        MemorizationTask task = new MemorizationTask();
        task.interpretation = interpretation;
        task.repo = repo;
        return task;
    }

}
