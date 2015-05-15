package ar.com.kfgodel.associative.impl.conditional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * This type implements the ConditionalMap
 * Created by kfgodel on 13/05/15.
 */
public class ConditionalMapImpl<K,V> implements ConditionalMap<K,V> {

    private LinkedHashMap<Predicate<K>, Optional<V>> bindings;

    @Override
    public Optional<V> getValueFor(K key) {
        Set<Map.Entry<Predicate<K>, Optional<V>>> entries = bindings.entrySet();
        for (Map.Entry<Predicate<K>, Optional<V>> entry : entries) {
            if(entry.getKey().test(key)){
                return entry.getValue();
            }
        }
        return Optional.empty();
    }

    @Override
    public void bind(Predicate<K> condition, V value) {
        if(value == null){
            throw new IllegalArgumentException("We don't support null as value for now");
        }
        this.bindings.put(condition,Optional.of(value));
    }

    public static ConditionalMapImpl create() {
        ConditionalMapImpl map = new ConditionalMapImpl();
        map.bindings = new LinkedHashMap<>();
        return map;
    }

}
