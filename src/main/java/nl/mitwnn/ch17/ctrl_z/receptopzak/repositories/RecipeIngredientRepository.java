package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pelle Meuzelaar
 * Interface for ingredient used in recipe
 */

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
