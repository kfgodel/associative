package ar.com.kfgodel.associative;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.associative.facade.api.CrudFacade;
import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.config.InterpretationConfiguration;
import ar.com.kfgodel.associative.objects.TestUser;

import java.util.function.Supplier;

/**
 * This type represents the testing context used in javaspec tests of this project
 * Created by kfgodel on 12/05/15.
 */
public interface AssociativeTestContext extends TestContext {

    InterpretationConfiguration config();
    void config(Supplier<InterpretationConfiguration> definition);

    <R> R entity();
    void entity(Supplier<Object> definition);

    EntityRepresentation interpretation();
    void interpretation(Supplier<EntityRepresentation> definition);


    CrudFacade facade();
    void facade(Supplier<CrudFacade> definition);

    TestUser user();
    void user(Supplier<TestUser> definition);

    Long persistedId();
    void persistedId(Supplier<Long> definition);


}
