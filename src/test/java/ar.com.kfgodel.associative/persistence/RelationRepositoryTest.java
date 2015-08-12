package ar.com.kfgodel.associative.persistence;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.associative.AssociativeTestContext;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kfgodel on 04/08/2015.
 */
@RunWith(JavaSpecRunner.class)
public class RelationRepositoryTest extends JavaSpec<AssociativeTestContext> {
    @Override
    public void define() {
        describe("a relation repository", ()->{
            it("stores persistent relations", ()->{
                RelationRepo repo = RelationRepoImpl.create();
                repo.add(PersistentRelation.create(6L, 1L, 2L));
                repo.add(PersistentRelation.create(6L, 1L, 3L));

                assertThat(repo.relationCount()).isEqualTo(2);
            });
            
            it("retrieves the object id that matches the given relation predicates", ()->{
                RelationRepo repo = RelationRepoImpl.create();
                repo.add(PersistentRelation.create(6L, 1L, 2L));
                repo.add(PersistentRelation.create(6L, 1L, 3L));

                ConceptPredicate conceptPredicate = ConceptPredicate.create(
                        RestrictedTypeAndDestination.create(1L, 2L),
                        RestrictedTypeAndDestination.create(1L, 3L));

                List<ConceptResult> matches = repo.findConcept(conceptPredicate).collect(Collectors.toList());
                assertThat(matches).hasSize(1);
                assertThat(matches.get(0).getSource()).isEqualTo(6L);
            });
            
            it("returns empty if no matching relation", ()->{
                RelationRepo repo = RelationRepoImpl.create();

                ConceptPredicate conceptPredicate = ConceptPredicate.create(
                        RestrictedTypeAndDestination.create(1L, 2L),
                        RestrictedTypeAndDestination.create(1L, 3L));

                List<ConceptResult> matches = repo.findConcept(conceptPredicate).collect(Collectors.toList());
                assertThat(matches).hasSize(0);
            });

            it("returns more than one option if available", ()->{
                RelationRepo repo = RelationRepoImpl.create();
                repo.add(PersistentRelation.create(6L, 1L, 2L));
                repo.add(PersistentRelation.create(6L, 1L, 3L));
                repo.add(PersistentRelation.create(1L, 1L, 2L));
                repo.add(PersistentRelation.create(1L, 1L, 3L));
                repo.add(PersistentRelation.create(7L, 1L, 2L));
                repo.add(PersistentRelation.create(7L, 1L, 4L));
                repo.add(PersistentRelation.create(8L, 2L, 2L));

                ConceptPredicate conceptPredicate = ConceptPredicate.create(
                        RestrictedTypeAndDestination.create(1L, 2L),
                        RestrictedTypeAndDestination.create(1L, 3L));

                List<ConceptResult> matches = repo.findConcept(conceptPredicate).collect(Collectors.toList());
                assertThat(matches).hasSize(2);
                assertThat(matches.get(0).getSource()).isEqualTo(6L);
                assertThat(matches.get(1).getSource()).isEqualTo(1L);
            });

            
            
        });
    }
}
