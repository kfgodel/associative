package ar.com.kfgodel.associative.memorization.impl.tasks;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.ObjectRepresentation;
import ar.com.kfgodel.associative.identification.api.RelationRepresentation;
import ar.com.kfgodel.associative.memorization.impl.memories.InterpretationMemoryImpl;
import ar.com.kfgodel.associative.persistence.ConceptPredicate;
import ar.com.kfgodel.associative.persistence.RelationPredicate;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;

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
        Nary<ObjectRepresentation> concepts = interpretation.concepts();
        for (ObjectRepresentation concept : concepts) {
            ConceptPredicate conceptPredicate = createPredicateFor(concept);
            Long conceptIdentificator = repo.storeConceptIdentity(conceptPredicate);
            Identity conceptIdentity = interpretation.identityOf(concept).get();
            memory.assignTo(conceptIdentity, conceptIdentificator);
        }
    }

    private ConceptPredicate createPredicateFor(ObjectRepresentation concept) {
        ConceptPredicate conceptPredicate = ConceptPredicate.create();
        for (Identity conceptRelationIdentity : concept.relations()) {
            Optional<RelationRepresentation> relationRepresentation = interpretation.representationOf(conceptRelationIdentity);
            relationRepresentation.get();
            Long restrictedType = memory.identificatorOf(relationRepresentation.get().relationType());
            Long restrictedDestination = memory.identificatorOf(relationRepresentation.get().destination());
            RelationPredicate relationPredicate = RelationPredicate.create(restrictedType, restrictedDestination);
            conceptPredicate.addPredicate(relationPredicate);
        }
        return conceptPredicate;
    }

    private void storeRelations() {
        for (RelationRepresentation relation : interpretation.relations()) {
            Long source = memory.identificatorOf(relation.origin());
            Long type = memory.identificatorOf(relation.relationType());
            Long destination = memory.identificatorOf(relation.destination());
            Long relationIdentificator = repo.storeRelation(source, type, destination);
            Identity relationIdentity = interpretation.identityOf(relation).get();
            memory.assignTo(relationIdentity, relationIdentificator);
        }
    }

    private void storePercepts() {
        for (Object percept : interpretation.percepts()) {
            Long perceptIdentificator = repo.storePercept(percept);
            Identity perceptIdentity = interpretation.identityOf(percept).get();
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
