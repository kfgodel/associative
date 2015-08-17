package ar.com.kfgodel.associative.materialization.api;

/**
 * This type represents the definition of how the expected object should be like
 * Created by kfgodel on 17/08/15.
 */
public interface ExpectedObjectDefinition {
    /**
     * The class that represents the expected type
     */
    Class<?> expectedClass();

    /**
     * The identificator of the expected object when stored in memory
     * @return
     */
    Long getIdentificator();
}
