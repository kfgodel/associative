package ar.com.kfgodel.associative.persistence;

import ar.com.kfgodel.hashcode.Hashcodes;

/**
 * Created by kfgodel on 04/08/2015.
 */
public class PersistentRelation {

    private Long source;
    private Long type;
    private Long destination;

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getDestination() {
        return destination;
    }

    public void setDestination(Long destination) {
        this.destination = destination;
    }

    public static PersistentRelation create(Long source, Long type, long destination) {
        PersistentRelation relation = new PersistentRelation();
        relation.source = source;
        relation.destination = destination;
        relation.type = type;
        return relation;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + getSource() + "-" + getType() + "->" + getDestination() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(!PersistentRelation.class.isInstance(obj)){
            return false;
        }
        PersistentRelation other = (PersistentRelation) obj;
        return this.getSource().equals(other.getSource()) && this.getType().equals(other.getType()) && this.getDestination().equals(other.getDestination());
    }

    @Override
    public int hashCode() {
        return Hashcodes.joining(getSource(), getType(), getDestination());
    }
}
