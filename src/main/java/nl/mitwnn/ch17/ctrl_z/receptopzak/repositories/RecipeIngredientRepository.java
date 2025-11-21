package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pelle Meuzelaar
 */

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    void deleteAllByIngredient_IngredientId(Long ingredientId);
}
