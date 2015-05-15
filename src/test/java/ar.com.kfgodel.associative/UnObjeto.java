package ar.com.kfgodel.associative;

import ar.com.kfgodel.tostring.ImplementedWithStringer;
import ar.com.kfgodel.tostring.Stringer;

/**
 * Created by kfgodel on 10/05/15.
 */
public class UnObjeto {

    private OtroObjeto otro;

    public OtroObjeto getOtro() {
        return otro;
    }

    public void setOtro(OtroObjeto otro) {
        this.otro = otro;
    }

    public static UnObjeto create() {
        UnObjeto unObjeto = new UnObjeto();
        unObjeto.otro = OtroObjeto.create();
        unObjeto.otro.setUno(unObjeto);
        return unObjeto;
    }

    @Override
    @ImplementedWithStringer
    public String toString() {
        return Stringer.representationOf(this);
    }
}
