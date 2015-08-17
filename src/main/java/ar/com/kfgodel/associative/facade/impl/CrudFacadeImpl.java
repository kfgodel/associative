package ar.com.kfgodel.associative.facade.impl;

import ar.com.kfgodel.associative.facade.api.CrudFacade;
import ar.com.kfgodel.associative.identification.impl.config.DefaultConfiguration;
import ar.com.kfgodel.associative.materialization.impl.TypeAndIdDefinition;
import ar.com.kfgodel.associative.materialization.impl.tasks.MaterializationTask;
import ar.com.kfgodel.associative.memorization.impl.tasks.MemoryCreationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.impl.DecomposerProcessor;
import ar.com.kfgodel.nary.api.Nary;

/**
 * Created by kfgodel on 01/08/15.
 */
public class CrudFacadeImpl implements CrudFacade {

    private DefaultConfiguration configuration;

    @Override
    public Long create(Object entity) {
        DecomposableTask creationTask = MemoryCreationTask.create(entity, configuration.getMagiRepository(), configuration);
        return DecomposerProcessor.create()
                .process(creationTask);
    }

    @Override
    public <T> Nary<T> retrieveById(Long identificator, Class<T> expectedType) {
        MaterializationTask materializationTask = MaterializationTask.create(TypeAndIdDefinition.create(expectedType, identificator), configuration);
        return DecomposerProcessor.create()
                .process(materializationTask);
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
        crudFacade.configuration = DefaultConfiguration.create();
        return crudFacade;
    }

}
