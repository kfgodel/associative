package ar.com.kfgodel.associative.facade.impl;

import ar.com.kfgodel.associative.facade.api.CrudFacade;
import ar.com.kfgodel.associative.identification.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.identification.impl.config.DefaultConfiguration;
import ar.com.kfgodel.associative.memorization.impl.tasks.MemoryCreationTask;
import ar.com.kfgodel.associative.memorization.impl.tasks.ObjectSummoningTask;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.associative.persistence.impl.magi.MagiRepoImpl;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.impl.DecomposerProcessor;
import ar.com.kfgodel.nary.api.Nary;

/**
 * Created by kfgodel on 01/08/15.
 */
public class CrudFacadeImpl implements CrudFacade {

    private MagiRepo repo;
    private InterpretationConfiguration interpreationConfig;

    @Override
    public Long create(Object entity) {
        DecomposableTask creationTask = MemoryCreationTask.create(entity, repo, interpreationConfig);
        return DecomposerProcessor.create()
                .process(creationTask);
    }

    @Override
    public <T> Nary<T> retrieveById(Long identificator, Class<T> expectedType) {
        final ObjectSummoningTask summoningTask = ObjectSummoningTask.create(identificator, expectedType, interpreationConfig);
        return DecomposerProcessor.create()
                .process(summoningTask);
    }

    @Override
    public <T> Nary<T> retrieveAllOf(Class<T> expectedType) {
        return null;
    }

    @Override
    public void update(Long identificator, Object newState) {

    }

    @Override
    public void delete(Long identificator, Class<?> expectedType) {

    }

    public static CrudFacadeImpl create() {
        CrudFacadeImpl crudFacade = new CrudFacadeImpl();
        crudFacade.repo = MagiRepoImpl.create();
        crudFacade.interpreationConfig = DefaultConfiguration.create();
        return crudFacade;
    }

}
