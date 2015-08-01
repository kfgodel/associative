package ar.com.kfgodel.associative.facade.api;

import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents an abstraction of the associative memory seen as a CRUD repository
 * Created by kfgodel on 01/08/15.
 */
public interface CrudFacade {

    /**
     * Creates a persistent state of the given object returning an identificator to retrieve it later
     * @param entity The object to persist
     * @return The identificator of the persisted state representing the given object
     */
    Long create(Object entity);

    /**
     * Retrieves the persistent state identified by the given identificator, with the structure of the given class
     * @param identificator The identificator used when the state was created
     * @param expectedType The type that indicates the form of the expected state
     * @param <T> The type of the expected object
     * @return The object created from the found state and given class, or empty if not found
     */
    <T> Nary<T> retrieveById(Long identificator, Class<T> expectedType);

    /**
     * Retrieves the persistent state of all the persisted objects of the given type
     * @param expectedType The class that defines the structure of expected persisted state
     * @param <T> The type of expected objects
     * @return The found objects or empty if none matches the given type
     */
    <T> Nary<T> retrieveAllOf(Class<T> expectedType);

    /**
     * Updates the persisted state identified by the identificator with the new state contained in the given object
     * @param identificator The identificator to reference the persistent state
     * @param newState The object that contains the updated state
     */
    void update(Long identificator, Object newState);

    /**
     * Deletes the persistent state identified by the identificator, using the given class to remove related data
     * @param identificator The identificator of the state to delete
     * @param expectedType The class that defines how much needs to be deleted
     */
    void delete(Long identificator, Class<?> expectedType);
}
