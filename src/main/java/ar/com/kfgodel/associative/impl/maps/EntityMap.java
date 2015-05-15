package ar.com.kfgodel.associative.impl.maps;

import ar.com.kfgodel.associative.api.Identity;

import java.util.Optional;

/**
 * Created by kfgodel on 14/05/15.
 */
public interface EntityMap {

    Optional<Identity> getIdentityFor(Object entity);

    void put(Object entity, Identity newIdentity);

    boolean containsIdentityFor(Object entity);
}
