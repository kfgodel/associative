package ar.com.kfgodel.associative.facade.impl;

import ar.com.kfgodel.associative.facade.api.CrudFacade;
import ar.com.kfgodel.nary.api.Nary;

/**
 * Created by kfgodel on 01/08/15.
 */
public class CrudFacadeImpl implements CrudFacade {
    @Override
    public Long create(Object object) {
        return null;
    }

    @Override
    public <T> Nary<T> retrieveById(Long identificator, Class<T> expectedType) {
        return null;
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
        return crudFacade;
    }

}
