package ar.com.kfgodel.associative.persistence.api.magi;

import ar.com.kfgodel.optionals.Optional;

/**
 * Created by kfgodel on 07/08/15.
 */
public interface PerceptRepo {
    /**
     * The identificator assigned to the given percept (found by equality)
     * @param percept The percept to find
     * @return The identificator that represents its identity across time
     */
    Optional<Long> getIdentificatorOf(Object percept);

    /**
     * Registers the percept with the given identificator
     * @param identificator The identificator to identify the percept
     * @param percept The percept assigned to the identificator
     */
    void persistWith(Long identificator, Object percept);
}
