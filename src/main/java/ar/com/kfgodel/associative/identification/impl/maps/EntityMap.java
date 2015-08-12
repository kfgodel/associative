package ar.com.kfgodel.associative.identification.impl.maps;

import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.optionals.Optional;

/**
 * Created by kfgodel on 14/05/15.
 */
public interface EntityMap {

    Optional<Identity> getIdentityFor(Object entity);

    void put(Object entity, Identity newIdentity);

    boolean containsIdentityFor(Object entity);
}
