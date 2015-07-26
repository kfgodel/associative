package ar.com.kfgodel.associative.impl.synthesizer;

import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.config.Synthesizer;
import ar.com.kfgodel.associative.api.context.InterpretationContext;
import ar.com.kfgodel.associative.impl.model.ObjectRepresentationImpl;

import java.util.List;

/**
 * Created by kfgodel on 14/05/15.
 */
public class UnknownTypeSynthesizer implements Synthesizer {
    @Override
    public Object synthesize(List<Identity> partIdentities, InterpretationContext context) {
        return ObjectRepresentationImpl.create(partIdentities);
    }

    public static UnknownTypeSynthesizer create() {
        UnknownTypeSynthesizer synthesizer = new UnknownTypeSynthesizer();
        return synthesizer;
    }

}
