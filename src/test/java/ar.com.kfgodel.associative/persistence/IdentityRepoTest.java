package ar.com.kfgodel.associative.persistence;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.associative.AssociativeTestContext;
import ar.com.kfgodel.associative.persistence.impl.magi.IdentityRepoImpl;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kfgodel on 07/08/15.
 */
@RunWith(JavaSpecRunner.class)
public class IdentityRepoTest extends JavaSpec<AssociativeTestContext> {
    @Override
    public void define() {
        describe("an identity repo", ()->{
            it("creates persisted identities, identified by a number",()->{
                IdentityRepoImpl repo = IdentityRepoImpl.create();

                Long firstIdentificator = repo.createNew();
                assertThat(firstIdentificator).isEqualTo(0L);

                Long secondIdentificator = repo.createNew();
                assertThat(secondIdentificator).isEqualTo(1L);
            });
        });
    }
}