package ar.com.kfgodel.associative.identification.impl.generator;

import ar.com.kfgodel.associative.identification.api.Identity;

/**
 * Created by kfgodel on 14/05/15.
 */
public class IdentityImpl implements Identity {

    private int discriminator;

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!IdentityImpl.class.isInstance(obj)){
            return false;
        }
        IdentityImpl other = (IdentityImpl) obj;
        return this.discriminator == other.discriminator;
    }

    @Override
    public int hashCode() {
        return discriminator;
    }

    @Override
    public String toString() {
        return "Identity<"+discriminator+">";
    }

    public static IdentityImpl create(int discriminator) {
        IdentityImpl identity = new IdentityImpl();
        identity.discriminator = discriminator;
        return identity;
    }

}
