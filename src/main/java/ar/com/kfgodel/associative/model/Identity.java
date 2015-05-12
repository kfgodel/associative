package ar.com.kfgodel.associative.model;

/**
 * This type represents the interpretation of an entity to which the associative memory uses to store information about
 * the entity.<br>
 * An identity is an abstraction of an entity created by a subjective agent (in this case the associative memory) to
 * track the evolution of an entity in time.<br>
 * From "identitas", probable  combination of id (thing) + ents (is) + tat (quality objetivation) "that which is";
 * idem (the same) entitas "the same being"; or idem et idem "the same and the same again".<br>
 * <br>
 * In the context of this project an identity is an internal reference to an external entity, which is used to reflect
 * the relationships that entity has in the external world, by emulating them in the internal representation.<br>
 * An identity by itself doesn't have value. It's in the relation with others, or itself, that conveys meaning.<br>
 * <br>
 * The only responsibility of an identity instance is to serve as discriminator with other identities, so they don't
 * get confused or mixed. It's only purpose is to serve as a comparable token which can be tell apart from other tokens.
 *
 * Created by kfgodel on 11/05/15.
 */
public interface Identity {

}
