package ar.com.kfgodel.associative.persistence.impl.magi;

import ar.com.kfgodel.associative.persistence.api.magi.PerceptRepo;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import ar.com.kfgodel.optionals.Optional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kfgodel on 07/08/15.
 */
public class PerceptRepoImpl implements PerceptRepo {

    private Map<Long, Object> perceptPerIdentificator;
    private Map<Object, Long> identificatorPerPercept;

    @Override
    public Optional<Long> getIdentificatorOf(Object percept) {
        Long foundIdentificator = identificatorPerPercept.get(percept);
        return NaryFromNative.ofNullable(foundIdentificator);
    }

    @Override
    public void persistWith(Long identificator, Object percept) {
        identificatorPerPercept.put(percept, identificator);
        perceptPerIdentificator.put(identificator, percept);
    }

    @Override
    public Optional<Object> retrieveById(Long identificator) {
        Object percept = perceptPerIdentificator.get(identificator);
        return NaryFromNative.ofNullable(percept);
    }

    public static PerceptRepoImpl create() {
        PerceptRepoImpl perceptRepo = new PerceptRepoImpl();
        perceptRepo.identificatorPerPercept = new LinkedHashMap<>();
        perceptRepo.perceptPerIdentificator = new HashMap<>();
        return perceptRepo;
    }

}
