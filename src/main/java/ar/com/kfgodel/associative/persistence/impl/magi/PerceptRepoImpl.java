package ar.com.kfgodel.associative.persistence.impl.magi;

import ar.com.kfgodel.associative.persistence.api.magi.PerceptRepo;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import ar.com.kfgodel.optionals.Optional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kfgodel on 07/08/15.
 */
public class PerceptRepoImpl implements PerceptRepo {

    private Map<Object, Long> identificatorPerPercept;
    @Override
    public Optional<Long> getIdentificatorOf(Object percept) {
        Long foundIdentificator = identificatorPerPercept.get(percept);
        return NaryFromNative.ofNullable(foundIdentificator);
    }

    @Override
    public void persistWith(Long identificator, Object percept) {
        identificatorPerPercept.put(percept, identificator);
    }

    public static PerceptRepoImpl create() {
        PerceptRepoImpl perceptRepo = new PerceptRepoImpl();
        perceptRepo.identificatorPerPercept = new LinkedHashMap<>();
        return perceptRepo;
    }

}
