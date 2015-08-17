package ar.com.kfgodel.associative.facade;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.associative.AssociativeTestContext;
import ar.com.kfgodel.associative.facade.impl.CrudFacadeImpl;
import ar.com.kfgodel.associative.objects.TestUser;
import ar.com.kfgodel.nary.api.Nary;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies that the associative memory can support a typical CRUD usage
 * Created by kfgodel on 01/08/15.
 */
@RunWith(JavaSpecRunner.class)
public class CrudTest extends JavaSpec<AssociativeTestContext> {
    @Override
    public void define() {

        describe("an associative facade", () -> {

            context().facade(CrudFacadeImpl::create);

            context().user(() -> TestUser.create("jsmith", "John Smith"));
            
            it("can be used to create a persistent state", () -> {

                TestUser user = context().user();

                Long identificator = context().facade().create(user);

                assertThat(identificator).isNotNull();
            });

            describe("with a persisted percept", () -> {
                context().persistedId(() ->
                        context().facade().create("Hola")
                );
                
                it("can be used to retrieve the percept by identificator",()->{
                    Long identificator = context().persistedId();

                    Nary<String> persistedState = context().facade().retrieveById(identificator, String.class);

                    assertThat(persistedState.isPresent()).isTrue();
                    assertThat(persistedState.get()).isEqualTo("Hola");
                });   
            });

            describe("with a created persistent state", () -> {

                context().persistedId(() -> context().facade().create(context().user()));

                it("can be used to retrieve the state by identificator", () -> {
                    Long identificator = context().persistedId();

                    Nary<TestUser> persistedState = context().facade().retrieveById(identificator, TestUser.class);

                    assertThat(persistedState.isPresent()).isTrue();
                    assertThat(persistedState.get().getName()).isEqualTo("jsmith");
                    assertThat(persistedState.get().getLoginId()).isEqualTo("John Smith");
                });

                it("can be used to retrieve all the persisted objects of a certain type", () -> {

                    List<TestUser> persistedUsers = context().facade().retrieveAllOf(TestUser.class)
                            .collect(Collectors.toList());

                    assertThat(persistedUsers).hasSize(1);
                    assertThat(persistedUsers.get(0).getName()).isEqualTo("jsmith");
                    assertThat(persistedUsers.get(0).getLoginId()).isEqualTo("John Smith");
                });

                xit("can be used to retrieve some of the persisted objects using a filter", () -> {

                });

                it("can be used to update the persisted object state", () -> {
                    Long persistedId = context().persistedId();

                    context().facade().update(persistedId, TestUser.create("jlsmith", "Josh L. Smith"));

                    Nary<TestUser> persistedState = context().facade().retrieveById(persistedId, TestUser.class);

                    assertThat(persistedState.isPresent()).isTrue();
                    assertThat(persistedState.get().getName()).isEqualTo("jlsmith");
                    assertThat(persistedState.get().getLoginId()).isEqualTo("Josh L. Smith");
                });

                it("can be used to delete the persisted object state", () -> {
                    Long persistedId = context().persistedId();

                    context().facade().delete(persistedId, TestUser.class);

                    Nary<TestUser> persistedState = context().facade().retrieveById(persistedId, TestUser.class);
                    assertThat(persistedState.isPresent()).isFalse();
                });
            });

        });


    }
}