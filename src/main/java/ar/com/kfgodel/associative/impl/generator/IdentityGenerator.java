package ar.com.kfgodel.associative.impl.generator;

import ar.com.kfgodel.associative.api.Identity;

/**
 * Created by kfgodel on 14/05/15.
 */
public interface IdentityGenerator {
    /**
     * Creates a new identity to be used for an unrecognized entity
     * @return The created identity
     */
    Identity createIdentity();

}
