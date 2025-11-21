package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @author Pelle Meuzelaar
 */

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByIngredientName(String ingredientName);
}
