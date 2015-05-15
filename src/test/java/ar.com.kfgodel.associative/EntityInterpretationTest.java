package ar.com.kfgodel.associative;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.associative.api.EntityInterpretation;
import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectInterpretation;
import ar.com.kfgodel.associative.api.RelationInterpretation;
import ar.com.kfgodel.associative.impl.config.DefaultConfiguration;
import ar.com.kfgodel.associative.impl.tasks.InterpretationTask;
import ar.com.kfgodel.decomposer.api.DecomposableTask;
import ar.com.kfgodel.decomposer.impl.DecomposerProcessor;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of a entity interpretation
 * Created by kfgodel on 12/05/15.
 */
@RunWith(JavaSpecRunner.class)
public class EntityInterpretationTest extends JavaSpec<AssociativeTestContext> {
    @Override
    public void define() {
        describe("an entity interpretation", () -> {

            context().config(DefaultConfiguration::create);
            
            it("is created from an entity with an interpretation task", () -> {
                DecomposableTask interpretationTask = InterpretationTask.create(UnObjeto.create(), context().config());

                EntityInterpretation interpretation = DecomposerProcessor.create()
                        .process(interpretationTask);

                assertThat(interpretation).isNotNull();
            });

            describe("captures", () -> {

                context().entity(UnObjeto::create);

                context().interpretation(() -> {
                    DecomposableTask interpretationTask = InterpretationTask.create(context().entity(), context().config());

                    return DecomposerProcessor.create().process(interpretationTask);
                });

                it("all the perceivable identities", () -> {
                    EntityInterpretation interpretation = context().interpretation();

                    // One for each object, one for each relation, one for the unconscious relation type
                    assertThat(interpretation.entities().count()).isEqualTo(5);

                    UnObjeto entity = context().entity();
                    Optional<Identity> entityIdentity = interpretation.identityOf(entity);
                    assertThat(entityIdentity.isPresent()).isTrue();

                    OtroObjeto otroObjeto = entity.getOtro();
                    Optional<Identity> otroIdentity = interpretation.identityOf(otroObjeto);
                    assertThat(otroIdentity.isPresent()).isTrue();

                    assertThat(entityIdentity.get()).isNotEqualTo(otroIdentity.get());
                });

                it("all the discovered objects", () -> {
                    EntityInterpretation interpretation = context().interpretation();

                    assertThat(interpretation.objects().count()).isEqualTo(2);

                    UnObjeto entity = context().entity();
                    Identity entityIdentity = interpretation.identityOf(entity).get();
                    Identity otroIdentity = interpretation.identityOf(entity.getOtro()).get();

                    Optional<ObjectInterpretation> entityRepresentation = interpretation.representationOf(entityIdentity);
                    assertThat(entityRepresentation.isPresent()).isTrue();

                    Optional<ObjectInterpretation> otroRepresentation = interpretation.representationOf(otroIdentity);
                    assertThat(otroRepresentation.isPresent()).isTrue();

                    assertThat(entityRepresentation.get()).isNotEqualTo(otroRepresentation.get());
                });

                it("all the discovered relations", () -> {
                    EntityInterpretation interpretation = context().interpretation();

                    assertThat(interpretation.objects().count()).isEqualTo(2);

                    UnObjeto entity = context().entity();
                    Identity entityIdentity = interpretation.identityOf(entity).get();
                    Identity otroIdentity = interpretation.identityOf(entity.getOtro()).get();

                    Optional<ObjectInterpretation> entityRepresentation = interpretation.representationOf(entityIdentity);
                    assertThat(entityRepresentation.get().relations().count()).isEqualTo(1);
                    Identity entitySourcedRelationIdentity = entityRepresentation.get().relations().findFirst().get();
                    RelationInterpretation entitySourcedRelation = interpretation.<RelationInterpretation>representationOf(entitySourcedRelationIdentity).get();
                    assertThat(entitySourcedRelation.origin()).isSameAs(entityIdentity);
                    assertThat(entitySourcedRelation.destination()).isSameAs(otroIdentity);

                    Optional<ObjectInterpretation> otroRepresentation = interpretation.representationOf(otroIdentity);
                    assertThat(otroRepresentation.get().relations().count()).isEqualTo(1);
                    Identity otroSourcedRelationIdentity = otroRepresentation.get().relations().findFirst().get();
                    RelationInterpretation otroSourcedRelation = interpretation.<RelationInterpretation>representationOf(otroSourcedRelationIdentity).get();
                    assertThat(otroSourcedRelation.origin()).isSameAs(otroIdentity);
                    assertThat(otroSourcedRelation.destination()).isSameAs(entityIdentity);
                });


            });


        });

    }
}
