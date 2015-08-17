package ar.com.kfgodel.associative.persistence.impl.magi;

import ar.com.kfgodel.associative.persistence.*;
import ar.com.kfgodel.associative.persistence.api.magi.IdentityRepo;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.associative.persistence.api.magi.PerceptRepo;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.optionals.Optional;

/**
 * Created by kfgodel on 07/08/15.
 */
public class MagiRepoImpl implements MagiRepo {

    private PerceptRepo percepts;
    private IdentityRepo identities;
    private RelationRepo relations;

    @Override
    public Long storePercept(Object percept) {
        Optional<Long> identificator = percepts.getIdentificatorOf(percept);
        return identificator.orElseGet(() -> createNewPerceptRecord(percept));
    }

    @Override
    public Long storeConceptIdentity(ConceptPredicate conceptPredicate) {
        // I'm assuming no more than one is found (solve DLG-2261)
        return relations.findConcept(conceptPredicate)
                .mapOptional(ConceptResult::getSource)
                .orElseGet(identities::createNew);
    }

    @Override
    public Long storeRelation(PersistentRelation persistentRelation) {
        Optional<Long> foundRelation = relations.getIdentificatorOf(persistentRelation);
        return foundRelation.orElseGet(() -> this.createRelationRecord(persistentRelation));
    }

    @Override
    public Optional<Long> retrievePerceptIdentificator(Object percept) {
        return percepts.getIdentificatorOf(percept);
    }

    @Override
    public Nary<ConceptResult> retrieveConcept(ConceptPredicate conceptPredicate) {
        return relations.findConcept(conceptPredicate);
    }

    @Override
    public Optional<Object> retrievePerceptById(Long identificator) {
        return percepts.retrieveById(identificator);
    }

    private Long createRelationRecord(PersistentRelation persistentRelation) {
        Long relationIdentificator = identities.createNew();
        relations.persistWith(relationIdentificator, persistentRelation);
        return relationIdentificator;
    }

    private Long createNewPerceptRecord(Object percept) {
        Long newIdentificator = identities.createNew();
        percepts.persistWith(newIdentificator, percept );
        return newIdentificator;
    }

    public static MagiRepoImpl create() {
        MagiRepoImpl magiRepo = new MagiRepoImpl();
        magiRepo.identities = IdentityRepoImpl.create();
        magiRepo.percepts = PerceptRepoImpl.create();
        magiRepo.relations = RelationRepoImpl.create();
        return magiRepo;
    }

}
