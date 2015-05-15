(This file serves as a reference of the concepts used for this project and their definition)

## Entity

 This type represents a concept that may not be used in the code, but it's important to capture in order to discriminate 
 it from Identity. An entity it's something that exists, something that is. Regardless of a subject perceiving it.  
 An entity represents something that is and has it's own qualities. From "entitas", a probable combination
 from ens (is) + tat (quality of object).
 
 In the context of this project an entity is every object that represents things outside the associative memory.  
 The associative memory creates interpretations of entities by using identities and relationships between them,
 thus an identity it's a reflection of an entity captured by this memory.
 
## Identity
 
This type represents the interpretation of an entity to which the associative memory uses to store information about
the entity.  
An identity is an abstraction of an entity created by a subjective agent (in this case the associative memory) to
track the evolution of an entity in time.  
From "identitas", probable  combination of id (thing) + ents (is) + tat (quality objetivation) "that which is";
idem (the same) entitas "the same being"; or idem et idem "the same and the same again".  

In the context of this project an identity is an internal reference to an external entity, which is used to reflect
the relationships that entity has in the external world, by emulating them in the internal representation.  
An identity by itself doesn't have value. It's in the relation with others, or itself, that conveys meaning.  

The only responsibility of an identity instance is to serve as discriminator with other identities, so they don't
get confused or mixed. It's only purpose is to serve as a comparable token which can be tell apart from other tokens.

## Percept

This type represents a perception made identity (survives time).  
A percept is an identity that is directly perceivable by the subject and it's storable in
the associative memory as an atomic unit. You can see it as something you can feel that cannot be described in terms of
 things.  
The percept is the building block from which concepts are composed. Concepts are divisible into percepts.  

## Concept

This type represents a construct of a sensitive being that captures a pattern of perceptions as an identity in itself.  
A concept, as opposed to percept, is an abstraction of multiple percepts and other concepts linked together to form
an identity. The concept captures the identity of an external entity, but also its perceivable qualities.  
While the identity it's the reflected essence of an entity, the concept is its meaning (that whcih gives value
to the identity).  

There are two basic types of concepts: relations and objects. Imagine them as different types of composites of identities

## Object

This type represents the most abstract concept which is the composition of concepts.  
An object is a set of relations between identities that form an identity in itself.  
It is the relation of relations, a meta-relation, that can also contain itself recursively. Thus being able to
represent any entity.

## Relation

This type represents a concept that binds together two identities using a third one that acts as medium.  
A relation is a directed construct that has an origin and a destination identities, and a relation type which
is used to discriminate them.  
The relation type (the third identity), serves as an activator of the relations allowing to hide or show certain
types of relations while others are hidden.  

The relations are the elements from which objects are made. The object is discovered by its relations.






