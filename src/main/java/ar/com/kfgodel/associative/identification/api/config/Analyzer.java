package ar.com.kfgodel.associative.identification.api.config;

import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the decomposer of an entity in parts that can be interpreted isolated
 * Created by kfgodel on 13/05/15.
 */
public interface Analyzer {
    /**
     * Decomposes the given entity into parts that can be interpreted
     * @param entity The entity to analyse
     * @return The parts analysed
     */
    Nary<Object> analyse(Object entity);
}
