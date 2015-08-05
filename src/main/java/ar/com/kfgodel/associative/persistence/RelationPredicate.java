package ar.com.kfgodel.associative.persistence;

/**
 * Created by kfgodel on 04/08/2015.
 */
public class RelationPredicate {

    private Long expectedType;
    private Long expectedDestination;

    public Long getExpectedType() {
        return expectedType;
    }

    public void setExpectedType(Long expectedType) {
        this.expectedType = expectedType;
    }

    public Long getExpectedDestination() {
        return expectedDestination;
    }

    public void setExpectedDestination(Long expectedDestination) {
        this.expectedDestination = expectedDestination;
    }

    public static RelationPredicate create(Long type, Long destination) {
        RelationPredicate predicate = new RelationPredicate();
        predicate.expectedType = type;
        predicate.expectedDestination = destination;
        return predicate;
    }

    public boolean matches(PersistentRelation relation) {
        return relation.getType().equals(expectedType) && relation.getDestination().equals(expectedDestination);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+ "{ type = " + expectedType + ", dest = " + expectedDestination + "}" ;
    }
}
