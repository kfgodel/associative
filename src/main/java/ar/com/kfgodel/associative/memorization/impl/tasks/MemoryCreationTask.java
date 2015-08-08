package ar.com.kfgodel.associative.memorization.impl.tasks;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.identification.impl.tasks.InterpretationTask;
import ar.com.kfgodel.associative.memorization.api.InterpretationMemory;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.decomposer.api.results.DelayResult;

/**
 * This task representsthe process of memory creation from the facade perspective
 * Created by kfgodel on 01/08/15.
 */
public class MemoryCreationTask implements DecomposableTask {

    private Object entity;
    private InterpretationConfiguration interpreationConfig;
    private MagiRepo repo;

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        return createEntityInterpretation();
    }

    private Object createEntityInterpretation() {
        InterpretationTask interpretationTask = InterpretationTask.create(entity, interpreationConfig);
        return DelayResult.waitingFor(interpretationTask)
                .andFinally(this::persistInterpretation);
    }

    private Object persistInterpretation(DecomposedContext taskContext) {
        EntityRepresentation interpretation = taskContext.getSubTaskResult();
        MemorizationTask memorizationTask = MemorizationTask.create(interpretation, repo);
        return DelayResult.waitingFor(memorizationTask)
                .andFinally(this::returnIdentificator);
    }

    private Long returnIdentificator(DecomposedContext taskContext) {
        InterpretationMemory memory = taskContext.getSubTaskResult();
        EntityRepresentation interpretation = memory.getInterpretation();
        Identity entityIdentity = interpretation.identityOf(entity).get();
        Long entityIdentificator = memory.identificatorOf(entityIdentity);
        return entityIdentificator;
    }

    public static MemoryCreationTask create(Object entity, MagiRepo repo) {
        MemoryCreationTask creationTask = new MemoryCreationTask();
        creationTask.entity = entity;
        creationTask.repo = repo;
        return creationTask;
    }

}
