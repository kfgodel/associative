package ar.com.kfgodel.associative.api;

/**
 * This type represents an entity relation interpreted by the associative memory. It's the internal representation
 * of a possible external relation.<br>
 * A relation is composed of 3 identities, an origin a destination and a type of relation (all represented as identities)
 * and represents a directed relation (non symmetric)
 *
 * Created by kfgodel on 12/05/15.
 */
public interface RelationRepresentation {

    /**
     * @return The identity that is identifiable as the source of the relation (one of the asymmetric  parts)
     */
    Identity origin();

    /**
     * @return The identity that qualifies the relation with a type
     */
    Identity relationType();

    /**
     * The identity that represents the end of the relation (one of the asymmetric parts)
     * @return
     */
    Identity destination();
}
