package ar.com.kfgodel.associative.impl.model;

import ar.com.kfgodel.associative.api.unknown.UninterpretableRepresentation;

/**
 * Created by kfgodel on 26/07/15.
 */
public class UninterpretableRepresentationImpl implements UninterpretableRepresentation {

    public static UninterpretableRepresentationImpl INSTANCE = new UninterpretableRepresentationImpl();

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
