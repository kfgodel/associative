package ar.com.kfgodel.associative.impl.maps;

import ar.com.kfgodel.associative.api.Identity;

import java.util.IdentityHashMap;
import java.util.Optional;

/**
 * Created by kfgodel on 14/05/15.
 */
public class EntityMapImpl implements EntityMap {

    private IdentityHashMap<Object, Identity> identitiesPerEntity;

    @Override
    public Optional<Identity> getIdentityFor(Object entity) {
        return Optional.ofNullable(identitiesPerEntity.get(entity));
    }

    @Override
    public void put(Object entity, Identity newIdentity) {
        this.identitiesPerEntity.put(entity, newIdentity);
    }

    public static EntityMapImpl create() {
        EntityMapImpl map = new EntityMapImpl();
        map.identitiesPerEntity = new IdentityHashMap<>();
        return map;
    }

}
