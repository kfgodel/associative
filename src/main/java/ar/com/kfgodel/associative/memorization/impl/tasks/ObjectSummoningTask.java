package ar.com.kfgodel.associative.memorization.impl.tasks;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.identification.impl.tasks.InterpretationTask;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.api.context.DecomposedContext;
import ar.com.kfgodel.decomposer.api.results.DelayResult;
import ar.com.kfgodel.diamond.api.Diamond;

/**
 * Created by kfgodel on 12/08/2015.
 */
public class ObjectSummoningTask implements DecomposableTask {

    private Long identificator;
    private Class expectedType;
    private Object summoned;
    private InterpretationConfiguration interpreterConfig;
    private MagiRepo repo;

    public static ObjectSummoningTask create(Long identificator, Class expectedType, MagiRepo repo, InterpretationConfiguration interpreterConfig) {
        ObjectSummoningTask task = new ObjectSummoningTask();
        task.identificator = identificator;
        task.expectedType = expectedType;
        task.interpreterConfig = interpreterConfig;
        task.repo = repo;
        return task;
    }

    @Override
    public Object executeUnder(DecomposedContext taskContext) {
        summoned = Diamond.of(expectedType).newInstance();
        final InterpretationTask interpretationTask = InterpretationTask.create(summoned, interpreterConfig);
        return DelayResult.waitingFor(interpretationTask)
                .andFinally(this::evokeMemory);
    }

    private Object evokeMemory(DecomposedContext taskContext) {
        EntityRepresentation representation = taskContext.getSubTaskResult();
        final MemoryEvocationTask evocationTask = MemoryEvocationTask.create(summoned, representation, identificator, repo);
        return DelayResult.waitingFor(evocationTask)
                .andFinally(this::shapeObjectToMemory);
    }

    private Object shapeObjectToMemory(DecomposedContext taskContext) {
        return null;
    }
}
