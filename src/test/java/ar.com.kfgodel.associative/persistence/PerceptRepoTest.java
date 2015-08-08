package ar.com.kfgodel.associative.persistence;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.associative.AssociativeTestContext;
import ar.com.kfgodel.associative.persistence.impl.magi.PerceptRepoImpl;
import ar.com.kfgodel.optionals.Optional;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kfgodel on 07/08/15.
 */
@RunWith(JavaSpecRunner.class)
public class PerceptRepoTest extends JavaSpec<AssociativeTestContext> {
    @Override
    public void define() {
        describe("a percept repo", () -> {
            it("stores percepts with their identification",()->{
                PerceptRepoImpl repo = PerceptRepoImpl.create();

                repo.persistWith(1L, "hola");
            });
            
            it("can retrieve the identificator of a known percept",()->{
                PerceptRepoImpl repo = PerceptRepoImpl.create();

                repo.persistWith(1L, "hola");

                Optional<Long> holaIdentificator = repo.getIdentificatorOf("hola");

                assertThat(holaIdentificator.isPresent()).isTrue();
                assertThat(holaIdentificator.get()).isEqualTo(1L);
            });   
        });

    }
}