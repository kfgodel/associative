package ar.com.kfgodel.associative.persistence.impl.magi;

import ar.com.kfgodel.associative.persistence.api.magi.IdentityRepo;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by kfgodel on 07/08/15.
 */
public class IdentityRepoImpl implements IdentityRepo {

    private long nextFreeIdentificator;
    private Set<Long> persistedIdentities;

    @Override
    public Long createNew() {
        long createdIdentificator = nextFreeIdentificator++;
        persistedIdentities.add(createdIdentificator);
        return createdIdentificator;
    }

    public static IdentityRepoImpl create() {
        IdentityRepoImpl repo = new IdentityRepoImpl();
        repo.nextFreeIdentificator = 0;
        repo.persistedIdentities = new LinkedHashSet<>();
        return repo;
    }

}
