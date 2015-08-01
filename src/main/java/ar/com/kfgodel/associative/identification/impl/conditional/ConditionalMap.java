package ar.com.kfgodel.associative.identification.impl.conditional;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * This type represents a Map like structure that binds a value with a condition.<br>
 *     To find a value the conditions are iterated in order, the first one to match is
 *     the one whose value is returned.<br>
 *     The insertion order is used to iterate the conditions
 * Created by kfgodel on 13/05/15.
 */
public interface ConditionalMap<K,V> {
    /**
     * Returns the value bounded to the condition that first matches the tested object
     * @param tested The object to evaluate
     * @return The value found or an empty optional if none matches
     */
    Optional<V> getFirstMatchingFor(K tested);

    /**
     * Creates a relationship between the condicion and the value, so the value is returned when the condition matches.<br>
     *     The order of invocation of this method determines the order of evaluation of the conditions
     * @param condition The condition to evaluate
     * @param value The value to use if the condition matches
     */
    void bind(Predicate<K> condition, V value);
}
