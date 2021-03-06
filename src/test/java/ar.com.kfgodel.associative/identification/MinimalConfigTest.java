package ar.com.kfgodel.associative.identification;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.associative.AssociativeTestContext;
import ar.com.kfgodel.associative.identification.api.EntityRepresentation;
import ar.com.kfgodel.associative.identification.api.Identity;
import ar.com.kfgodel.associative.identification.impl.config.MinimalConfiguration;
import ar.com.kfgodel.associative.identification.impl.model.UninterpretableRepresentationImpl;
import ar.com.kfgodel.associative.identification.impl.tasks.InterpretationTask;
import ar.com.kfgodel.associative.objects.UnObjeto;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.impl.DecomposerProcessor;
import ar.com.kfgodel.optionals.Optional;
import org.junit.runner.RunWith;

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
