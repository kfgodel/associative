package ar.com.kfgodel.associative.memorization.impl.memories;

import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.memorization.api.InterpretationMemory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kfgodel on 01/08/15.
 */
public class InterpretationMemoryImpl implements InterpretationMemory {

    private EntityRepresentation interpretation;
    private Map<Identity, Long> identificatorPerIdentity;


    @Override
    public EntityRepresentation getInterpretation() {
        return interpretation;
    }

    @Override
    public Long identificatorOf(Identity identity) {
        return identificatorPerIdentity.get(identity);
    }


    public static InterpretationMemoryImpl create(EntityRepresentation interpretation) {
        InterpretationMemoryImpl memory = new InterpretationMemoryImpl();
        memory.interpretation = interpretation;
        memory.identificatorPerIdentity = new LinkedHashMap<>();
        return memory;
    }

    public void assignTo(Identity identity, Long identificator) {
        this.identificatorPerIdentity.put(identity, identificator);
    }
}
