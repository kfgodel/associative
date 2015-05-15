package ar.com.kfgodel.associative.api;

import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the interpretation of an entity with its qualities.<br>
 *     An object is composed of relations
 *
 * Created by kfgodel on 12/05/15.
 */
public interface ObjectInterpretation {

    /**
     * The relations that form this object
     * @return The set of relations that make the pattern of identities for this object
     */
    Nary<Identity> relations();
}
