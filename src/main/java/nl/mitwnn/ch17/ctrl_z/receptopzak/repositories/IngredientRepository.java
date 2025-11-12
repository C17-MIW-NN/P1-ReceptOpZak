package nl.mitwnn.ch17.ctrl_z.receptopzak.repositories;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pelle Meuzelaar
 * Purpose for the class
 */

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
