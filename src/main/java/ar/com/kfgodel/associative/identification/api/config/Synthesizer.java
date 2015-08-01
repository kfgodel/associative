package ar.com.kfgodel.associative.identification.api.config;

import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.context.InterpretationContext;

import java.util.List;

/**
 * This type represents the composer of an entity interpretation from its part interpretations
 * Created by kfgodel on 13/05/15.
 */
public interface Synthesizer {
    /**
     * Creates an interpretation of the entity based on its parts already interpreted
     * @param partIdentities The identities of the parts
     * @param context The context to look for parts interpretation
     * @return The synthesized interpretation
     */
    Object synthesize(List<Identity> partIdentities, InterpretationContext context);
}
