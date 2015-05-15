package ar.com.kfgodel.associative.impl.maps;

import ar.com.kfgodel.associative.api.Identity;

/**
 * Created by kfgodel on 14/05/15.
 */
public interface InterpretationMap {
    void put(Identity entityIdentity, Object entityInterpretation);
}
