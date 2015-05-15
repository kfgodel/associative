package ar.com.kfgodel.associative;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.associative.api.EntityInterpretation;
import ar.com.kfgodel.associative.api.config.InterpretationConfiguration;

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

    EntityInterpretation interpretation();
    void interpretation(Supplier<EntityInterpretation> definition);



}
