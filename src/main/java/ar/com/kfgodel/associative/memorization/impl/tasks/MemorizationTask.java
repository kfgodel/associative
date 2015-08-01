package ar.com.kfgodel.associative.memorization.impl.tasks;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.RelationRepresentation;
import ar.com.kfgodel.associative.memorization.api.MemoryNetwork;
import ar.com.kfgodel.associative.memorization.impl.memories.InterpretationMemoryImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;

import java.util.Iterator;

/**
 * Created by kfgodel on 01/08/15.
 */
public class MemorizationTask implements DecomposableTask {

    private EntityRepresentation interpretation;
    private MemoryNetwork storage;
    private InterpretationMemoryImpl memory;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        memory = InterpretationMemoryImpl.create(interpretation);
        storePercepts();
        storeRelations();
        storeConcepts();
        return memory;
    }

    private void storeConcepts() {

    }

    private void storeRelations() {
        Iterator<RelationRepresentation> relations = interpretation.relations().iterator();
        while(relations.hasNext()){
            RelationRepresentation relation = relations.next();

        }
    }

    private void storePercepts() {
        Iterator<Object> percepts = interpretation.percepts().iterator();
        while(percepts.hasNext()){
            Object percept = percepts.next();
            Long perceptIdentificator = storage.store(percept);
            Identity perceptIdentity = interpretation.identityOf(percept).get();
            memory.assignTo(perceptIdentity, perceptIdentificator);
        }
    }

    public static MemorizationTask create(EntityRepresentation interpretation) {
        MemorizationTask task = new MemorizationTask();
        task.interpretation = interpretation;
        return task;
    }

}
