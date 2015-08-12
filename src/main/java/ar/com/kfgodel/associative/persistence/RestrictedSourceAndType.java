package ar.com.kfgodel.associative.persistence;

/**
 * Created by kfgodel on 04/08/2015.
 */
public class RestrictedSourceAndType implements RelationPredicate {

    private Long expectedType;
    private Long expectedSource;

    public static RelationPredicate create(Long source, Long type) {
        RestrictedSourceAndType predicate = new RestrictedSourceAndType();
        predicate.expectedSource = source;
        predicate.expectedType = type;
        return predicate;
    }

    @Override
    public boolean matches(PersistentRelation relation) {
        return relation.getSource().equals(expectedSource) && relation.getType().equals(expectedType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+ "{ source = " + expectedSource + ", type = " + expectedType + "}" ;
    }
}
