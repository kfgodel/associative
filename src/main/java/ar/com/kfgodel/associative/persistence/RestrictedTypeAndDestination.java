package ar.com.kfgodel.associative.persistence;

/**
 * Created by kfgodel on 04/08/2015.
 */
public class RestrictedTypeAndDestination implements RelationPredicate {

    private Long expectedType;
    private Long expectedDestination;

    public Long getExpectedType() {
        return expectedType;
    }

    public void setExpectedType(Long expectedType) {
        this.expectedType = expectedType;
    }

    public static RelationPredicate create(Long type, Long destination) {
        RestrictedTypeAndDestination predicate = new RestrictedTypeAndDestination();
        predicate.expectedType = type;
        predicate.expectedDestination = destination;
        return predicate;
    }

    @Override
    public boolean matches(PersistentRelation relation) {
        return relation.getType().equals(expectedType) && relation.getDestination().equals(expectedDestination);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+ "{ type = " + expectedType + ", dest = " + expectedDestination + "}" ;
    }
}
