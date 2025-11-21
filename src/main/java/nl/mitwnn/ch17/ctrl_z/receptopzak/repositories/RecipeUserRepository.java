package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @author Pelle Meuzelaar
 */

public interface RecipeUserRepository extends JpaRepository<RecipeUser, Long> {
    Optional<RecipeUser> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
