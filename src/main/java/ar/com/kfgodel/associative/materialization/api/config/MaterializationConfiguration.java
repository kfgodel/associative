package ar.com.kfgodel.associative.materialization.api.config;

import ar.com.kfgodel.associative.identification.impl.conditional.ConditionalMap;
import ar.com.kfgodel.associative.materialization.api.ExpectedObjectDefinition;
import ar.com.kfgodel.associative.persistence.api.magi.MagiRepo;

/**
 * This type represents the components configuration needed to re-create entities from memories
 * Created by kfgodel on 17/08/15.
 */
public interface MaterializationConfiguration {

    ConditionalMap<ExpectedObjectDefinition,Materializer> materializers();

    MagiRepo getMagiRepository();
}
