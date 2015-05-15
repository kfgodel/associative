package ar.com.kfgodel.associative.impl.analyzer;

import ar.com.kfgodel.associative.api.config.Analyzer;
import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by kfgodel on 14/05/15.
 */
public class BoundFieldAnalyzer implements Analyzer {

    @Override
    public Nary<Object> analyse(Object entity) {
        BoundField field = (BoundField) entity;
        Object origin = field.instance();
        Object destination = field.get();
        ArrayList<Object> parts = Lists.newArrayList(origin, null, destination);
        return NaryFromNative.create(parts.stream());
    }

    public static BoundFieldAnalyzer create() {
        BoundFieldAnalyzer analyzer = new BoundFieldAnalyzer();
        return analyzer;
    }

}
