package ar.com.kfgodel.associative.impl.analyzer;

import ar.com.kfgodel.associative.api.config.Analyzer;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.nary.api.Nary;

/**
 * Created by kfgodel on 14/05/15.
 */
public class UnknownTypeAnalyzer implements Analyzer {

    @Override
    public Nary<Object> analyse(Object entity) {
        Nary entityFields = Diamond.metaObjects().from(entity).fields().all();
        return entityFields;
    }

    public static UnknownTypeAnalyzer create() {
        UnknownTypeAnalyzer analyzer = new UnknownTypeAnalyzer();
        return analyzer;
    }

}
