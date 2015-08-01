package ar.com.kfgodel.associative.identification.impl.synthesizer;

import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.api.config.Synthesizer;
import ar.com.kfgodel.associative.identification.api.context.InterpretationContext;
import ar.com.kfgodel.associative.identification.impl.model.ObjectRepresentationImpl;

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
