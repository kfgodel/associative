package ar.com.kfgodel.associative.identification.impl.generator;

import ar.com.kfgodel.associative.identification.api.Identity;

/**
 * Created by kfgodel on 14/05/15.
 */
public class IdentityGeneratorImpl implements IdentityGenerator {

    private int nextDiscriminator;

    @Override
    public Identity createIdentity() {
        return IdentityImpl.create(nextDiscriminator++);
    }

    public static IdentityGeneratorImpl create() {
        IdentityGeneratorImpl generator = new IdentityGeneratorImpl();
        generator.nextDiscriminator = 0;
        return generator;
    }

}
