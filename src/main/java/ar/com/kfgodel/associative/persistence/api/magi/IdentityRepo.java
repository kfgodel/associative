package ar.com.kfgodel.associative.persistence.api.magi;

/**
 * Created by kfgodel on 07/08/15.
 */
public interface IdentityRepo {
    /**
     * Creates a new identity identified by a numeric identificator
     * @return The identificator for the identity that spans across time
     */
    Long createNew();
}
