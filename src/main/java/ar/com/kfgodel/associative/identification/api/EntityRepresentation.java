package ar.com.kfgodel.associative.identification.api;

import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;

/**
 * This type represents the internal representation of an entity for an associative memory
 *
 * Created by kfgodel on 13/05/15.
 */
public interface EntityRepresentation {
    /**
     * @return The identities found in the entity (as a whole or as parts)
     */
    Nary<Identity> identities();

    /**
     * @return The identities of the interpreted relations between the entity and its parts
     */
    Nary<Identity> relations();

    /**
     * @return The interpreted objects that are identified as the entity or its parts
     */
    Nary<Identity> concepts();

    /**
     * @return The interpreted percepts that compose the entity
     */
    Nary<Identity> percepts();

    /**
     * Returns the identity given in this interpretation to the given object
     * @param reference The object used as a reference for the identity
     * @return The interpreted identity or an empty optional
     */
    Optional<Identity> identityOf(Object reference);

    /**
     * Returns the internal interpretation related to the given identity that is part
     * of the entity
     * @param reference The identity to get its interpretation
     * @param <R> The type of expected interpretation
     * @return The interpretation created for the given identity or empty if there's no such thing in this instance
     */
    <R> Optional<R> representationOf(Identity reference);

}
