package ar.com.kfgodel.associative.identification.impl.maps;

import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import ar.com.kfgodel.optionals.Optional;

import java.util.IdentityHashMap;

/**
 * Created by kfgodel on 14/05/15.
 */
public class EntityMapImpl implements EntityMap {

    private IdentityHashMap<Object, Identity> identitiesPerEntity;

    @Override
    public Optional<Identity> getIdentityFor(Object entity) {
        return NaryFromNative.ofNullable(identitiesPerEntity.get(entity));
    }

    @Override
    public void put(Object entity, Identity newIdentity) {
        this.identitiesPerEntity.put(entity, newIdentity);
    }

    @Override
    public boolean containsIdentityFor(Object entity) {
        return identitiesPerEntity.containsKey(entity);
    }

    public static EntityMapImpl create() {
        EntityMapImpl map = new EntityMapImpl();
        map.identitiesPerEntity = new IdentityHashMap<>();
        return map;
    }

}
