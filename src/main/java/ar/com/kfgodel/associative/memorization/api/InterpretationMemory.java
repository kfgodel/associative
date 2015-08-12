package ar.com.kfgodel.associative.memorization.api;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;

/**
 * This type represents the perssitent memory of an entity interpretation
 * Created by kfgodel on 01/08/15.
 */
public interface InterpretationMemory {

    EntityRepresentation getInterpretation();

    /**
     * Returns the identificator assigned to the persisted state of the given identity
     * @param identity The identity to retrieve its identificator
     * @return The assigned identificator
     */
    Long identificatorOf(Identity identity);
}
