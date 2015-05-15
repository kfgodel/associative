package ar.com.kfgodel.associative;

import ar.com.kfgodel.tostring.ImplementedWithStringer;
import ar.com.kfgodel.tostring.Stringer;

/**
 * Created by kfgodel on 10/05/15.
 */
public class OtroObjeto {

    private UnObjeto uno;

    public UnObjeto getUno() {
        return uno;
    }

    public void setUno(UnObjeto uno) {
        this.uno = uno;
    }

    public static OtroObjeto create() {
        OtroObjeto otroObjeto = new OtroObjeto();
        return otroObjeto;
    }

    @Override
    @ImplementedWithStringer
    public String toString() {
        return Stringer.representationOf(this);
    }
}
