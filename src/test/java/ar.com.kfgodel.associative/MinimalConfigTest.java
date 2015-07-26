package ar.com.kfgodel.associative;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.associative.api.EntityRepresentation;
import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.impl.config.MinimalConfiguration;
import ar.com.kfgodel.associative.impl.model.UninterpretableRepresentationImpl;
import ar.com.kfgodel.associative.impl.tasks.InterpretationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.impl.DecomposerProcessor;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kfgodel on 19/05/2015.
 */
@RunWith(JavaSpecRunner.class)
public class MinimalConfigTest extends JavaSpec<AssociativeTestContext> {
    @Override
    public void define() {
        describe("a minimal configuration", () -> {
            context().config(MinimalConfiguration::create);

            context().entity(UnObjeto::create);

            context().interpretation(() -> {
                DecomposableTask interpretationTask = InterpretationTask.create(context().entity(), context().config());

                return DecomposerProcessor.create()
                        .process(interpretationTask);
            });

            it("interprets everything as uninterpretable", () -> {
                final EntityRepresentation interpretation = context().interpretation();

                final Optional<Identity> entityIdentity = interpretation.identityOf(context().entity());
                assertThat(entityIdentity.isPresent()).isTrue();

                final Optional<Object> entityRepresentation = interpretation.representationOf(entityIdentity.get());
                assertThat(entityRepresentation.get()).isEqualTo(UninterpretableRepresentationImpl.INSTANCE);
            });

            it("recognizes only one identity", () -> {
                final EntityRepresentation interpretation = context().interpretation();

                assertThat(interpretation.identities().count()).isEqualTo(1);
            });
        });
    }
}
