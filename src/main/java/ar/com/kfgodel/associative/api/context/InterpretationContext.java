package ar.com.kfgodel.associative.api.context;

import ar.com.kfgodel.associative.api.EntityInterpretation;
import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.config.Analyzer;
import ar.com.kfgodel.associative.api.config.Synthesizer;
import ar.com.kfgodel.decomposer.api.DecomposableTask;

import java.util.Optional;

/**
 * This type represents a context in which an interpretation happens and holds references
 * to the involved objects
 * Created by kfgodel on 13/05/15.
 */
public interface InterpretationContext {


    /**
     * Returns the interpretation process that best interprets the given object as a task
     * that can be executed and whose final result is an identity
     *
     * @param entity The entity to interpret
     * @return The task to do it
     */
    DecomposableTask getBestInterpretationProcessFor(Object entity);

    /**
     * @return The created interpretation of this context
     */
    EntityInterpretation createInterpretation();

    /**
     * Returns the analyzer that best decomposes the given entity into parts
     * @param entity The entity to decompose
     * @return The analyzer
     */
    Analyzer getBestAnalyzerFor(Object entity);

    /**
     * Returns the synthesizer that best composes the interpretation parts of an entity into a single interpretation
     * @param entity The entity to synthesize
     * @return The synthesizer
     */
    Synthesizer getBestSynthesizerFor(Object entity);

    /**
     * Gets or creates the identity for the given entity and stores the relaton to be reused
     * @param entity The entity to fetch its identity in this context
     * @return The given identity
     */
    Identity getOrCreateIdentityFor(Object entity);

    /**
     * Creates a new identity in this context bound to the given entity
     * @param entity The entity for which a new identity is going to be generated
     * @return The assigned identity for the entity in this context
     */
    Identity createIdentityFor(Object entity);

    /**
     * Stores the interpretation created in this context for the given identity
     * @param entityIdentity The identity to relate to the interpretation
     * @param entityInterpretation The created interpretation
     */
    void storeInterpretation(Identity entityIdentity, Object entityInterpretation);

    /**
     * Returns the optional identity for the passed entity (if exists)
     * @param entity The entity whose identity is requested
     * @return The optional identity given in this context, or empty if none is found
     */
    Optional<Identity> getIdentityFor(Object entity);
}
