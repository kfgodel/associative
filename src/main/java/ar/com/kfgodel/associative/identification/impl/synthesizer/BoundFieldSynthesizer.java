package ar.com.kfgodel.associative.identification.impl.synthesizer;

import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.config.Synthesizer;
import ar.com.kfgodel.associative.identification.api.context.InterpretationContext;
import ar.com.kfgodel.associative.identification.impl.model.RelationRepresentationImpl;

import java.util.List;

/**
 * Created by kfgodel on 14/05/15.
 */
public class BoundFieldSynthesizer implements Synthesizer {

    @Override
    public Object synthesize(List<Identity> partIdentities, InterpretationContext context) {
        RelationRepresentationImpl interpretation = RelationRepresentationImpl.create(partIdentities.get(0), partIdentities.get(1), partIdentities.get(2));
        return interpretation;
    }

    public static BoundFieldSynthesizer create() {
        BoundFieldSynthesizer synthesizer = new BoundFieldSynthesizer();
        return synthesizer;
    }

}
