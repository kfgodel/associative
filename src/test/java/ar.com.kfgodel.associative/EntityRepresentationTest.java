package ar.com.kfgodel.associative;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.associative.api.EntityRepresentation;
import ar.com.kfgodel.associative.api.Identity;
import ar.com.kfgodel.associative.api.ObjectRepresentation;
import ar.com.kfgodel.associative.api.RelationRepresentation;
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
public class EntityRepresentationTest extends JavaSpec<AssociativeTestContext> {
    @Override
    public void define() {
        describe("an entity interpretation", () -> {

            context().config(DefaultConfiguration::create);
            
            it("is created from an entity with an interpretation task", () -> {
                DecomposableTask interpretationTask = InterpretationTask.create(UnObjeto.create(), context().config());

                EntityRepresentation interpretation = DecomposerProcessor.create()
                        .process(interpretationTask);

                assertThat(interpretation).isNotNull();
            });

            describe("captures", () -> {

                context().entity(UnObjeto::create);

                context().interpretation(() -> {
                    DecomposableTask interpretationTask = InterpretationTask.create(context().entity(), context().config());

                    return DecomposerProcessor.create().process(interpretationTask);
                });

                describe("all the perceivable identities", () -> {

                    it("count",()->{
                        EntityRepresentation interpretation = context().interpretation();

                        // One for each object (2), one for each relation(3), one for the unconscious relation type, one for the string percept
                        assertThat(interpretation.identities().count()).isEqualTo(7);
                    });

                    it("presence",()->{
                        EntityRepresentation interpretation = context().interpretation();

                        UnObjeto unObjeto = context().entity();
                        Optional<Identity> unObjetoIdentity = interpretation.identityOf(unObjeto);
                        assertThat(unObjetoIdentity.isPresent()).isTrue();

                        OtroObjeto otroObjeto = unObjeto.getOtro();
                        Optional<Identity> otroIdentity = interpretation.identityOf(otroObjeto);
                        assertThat(otroIdentity.isPresent()).isTrue();

                        Optional<Identity> textoIdentity = interpretation.identityOf(unObjeto.getTexto());
                        assertThat(textoIdentity.isPresent()).isTrue();
                    });

                    it("difference",()->{
                        EntityRepresentation interpretation = context().interpretation();

                        UnObjeto unObjeto = context().entity();
                        OtroObjeto otroObjeto = unObjeto.getOtro();
                        String texto = unObjeto.getTexto();

                        Optional<Identity> unObjetoIdentity = interpretation.identityOf(unObjeto);

                        Optional<Identity> otroIdentity = interpretation.identityOf(otroObjeto);

                        Optional<Identity> textIdentity = interpretation.identityOf(texto);

                        // Las identidades de los objetos son distintas
                        assertThat(unObjetoIdentity.get()).isNotEqualTo(otroIdentity.get());
                        assertThat(textIdentity.get()).isNotEqualTo(unObjetoIdentity.get());
                        assertThat(textIdentity.get()).isNotEqualTo(otroIdentity.get());

                    });

                });


                it("all the discovered objects", () -> {
                    EntityRepresentation interpretation = context().interpretation();

                    assertThat(interpretation.objects().count()).isEqualTo(2);

                    UnObjeto entity = context().entity();
                    Identity entityIdentity = interpretation.identityOf(entity).get();
                    Identity otroIdentity = interpretation.identityOf(entity.getOtro()).get();

                    Optional<ObjectRepresentation> entityRepresentation = interpretation.representationOf(entityIdentity);
                    assertThat(entityRepresentation.isPresent()).isTrue();

                    Optional<ObjectRepresentation> otroRepresentation = interpretation.representationOf(otroIdentity);
                    assertThat(otroRepresentation.isPresent()).isTrue();

                    assertThat(entityRepresentation.get()).isNotEqualTo(otroRepresentation.get());
                });

                it("all the discovered relations", () -> {
                    EntityRepresentation interpretation = context().interpretation();

                    assertThat(interpretation.objects().count()).isEqualTo(2);

                    UnObjeto ubObjeto = context().entity();
                    Identity unObjetoIdentity = interpretation.identityOf(ubObjeto).get();
                    Identity otroIdentity = interpretation.identityOf(ubObjeto.getOtro()).get();
                    Identity textoIdentity = interpretation.identityOf(ubObjeto.getTexto()).get();

                    Optional<ObjectRepresentation> entityRepresentation = interpretation.representationOf(unObjetoIdentity);
                    assertThat(entityRepresentation.get().relations().count()).isEqualTo(2);

                    Identity unObjetoFirstRelationIdentity = entityRepresentation.get().relations().findFirst().get();
                    RelationRepresentation unObjetoFirstRelation = interpretation.<RelationRepresentation>representationOf(unObjetoFirstRelationIdentity).get();
                    assertThat(unObjetoFirstRelation.origin()).isSameAs(unObjetoIdentity);
                    assertThat(unObjetoFirstRelation.destination()).isSameAs(otroIdentity);

                    Identity unObjetoSecondRelationIdentity = entityRepresentation.get().relations().skip(1).findFirst().get();
                    RelationRepresentation unObjetoSecondRelation = interpretation.<RelationRepresentation>representationOf(unObjetoSecondRelationIdentity).get();
                    assertThat(unObjetoSecondRelation.origin()).isSameAs(unObjetoIdentity);
                    assertThat(unObjetoSecondRelation.destination()).isSameAs(textoIdentity);


                    Optional<ObjectRepresentation> otroRepresentation = interpretation.representationOf(otroIdentity);
                    assertThat(otroRepresentation.get().relations().count()).isEqualTo(1);
                    Identity otroRelationIdentity = otroRepresentation.get().relations().findFirst().get();
                    RelationRepresentation otroRelation = interpretation.<RelationRepresentation>representationOf(otroRelationIdentity).get();
                    assertThat(otroRelation.origin()).isSameAs(otroIdentity);
                    assertThat(otroRelation.destination()).isSameAs(unObjetoIdentity);
                });
            });
        });
    }
}
