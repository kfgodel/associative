package ar.com.kfgodel.associative.persistence.impl.magi;

import ar.com.kfgodel.associative.persistence.*;
import ar.com.kfgodel.associative.persistence.api.magi.IdentityRepo;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;
import ar.com.kfgodel.associative.persistence.api.magi.PerceptRepo;
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
        return identificator.orElseGet(()-> createNewPerceptRecord(percept));
    }

    @Override
    public Long storeConceptIdentity(ConceptPredicate conceptPredicate) {
        // I'm assuming no more than one is found (solve DLG-2261)
        return relations.findConcept(conceptPredicate)
                .mapOptional(ConceptResult::getSource)
                .orElseGet(identities::createNew);
    }

    @Override
    public Long storeRelation(Long source, Long type, Long destination) {
        Optional<PersistentRelation> relations.findRelation(source, type, destination);
        return null;
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
